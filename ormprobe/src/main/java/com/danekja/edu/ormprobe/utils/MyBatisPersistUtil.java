package com.danekja.edu.ormprobe.utils;

import com.danekja.edu.ormprobe.domain.*;
import com.danekja.edu.ormprobe.persistance.GroupMapper;
import com.danekja.edu.ormprobe.persistance.ItemMapper;
import com.danekja.edu.ormprobe.persistance.OwnershipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;

/**
 * Created by witz on 28.3.15.
 */
public class MyBatisPersistUtil implements DatabasePersistUtil {

  private DataSourceTransactionManager transactionManager;
  private TransactionStatus transactionStatus;

  @Autowired
  private GroupMapper groupMapper;
  @Autowired
  private OwnershipMapper ownershipMapper;
  @Autowired
  private ItemMapper itemMapper;

  @Autowired
  public void setTransactionManager(DataSourceTransactionManager manager) {
    transactionManager = manager;
  }

  public void beginTransaction() {
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    transactionStatus = transactionManager.getTransaction(def);
  }

  public void persistData(BaseObject object) {
    try {
      if (object instanceof Group || object instanceof BigGroup) {
        groupMapper.insert((Group) object);
      }else if(object instanceof OwnershipGroup || object instanceof OwnershipItem) {
        ownershipMapper.insert((Ownership) object);
      } else if(object instanceof Item) {
        itemMapper.insert((Item) object);
      }
    }catch (Exception e) {
      transactionManager.rollback(transactionStatus);
    }
  }

  public void commitChanges() {
    transactionManager.commit(transactionStatus);
  }

  public void endConnection() {
    try {
      transactionManager.getDataSource().getConnection().close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public BaseObject selectObjectById(Class clazz, Long id) {
    if (clazz == Group.class || clazz == BigGroup.class) {
      return groupMapper.getById(id);
    }else if(clazz == Item.class) {
      return itemMapper.getById(id);
    }else if(clazz == OwnershipItem.class || clazz == OwnershipGroup.class) {
      return ownershipMapper.getById(id);
    }
    else
      return null;
  }
}
