src
|— main
    |— java
        |— com.danekja.edu.ormprobe
            |— dao: obsahuje pouze zadane rozhrani TestDao
            |— domain: Java beans popisujici model aplikace
            |— mappers: obsahuje rozhrani s prototypy metod, ktere jsou mapovany
                            na SQL dotazy
            |— service: tridy obalujici volani metod z rozhrani definovanych v persistance
            |— utils: obsahuje rozhrani DatabasePersistUtil, ktere definuje metody potrebne
                        pro ulozeni dat vytvorenych generatorem; dale je ze samotny generator
                        a implementace DatabasePersistUtil pro MyBatis
    |— resources
        |— com.danekja.edu.ormprobe
            |— persistance: XML soubory obsahujici mapovani metod na SQL
        |— jdbc.properties: obsahuje nastaveni pripojeni k DB
        |— mybatis-config.xml: konfigurace MyBatis - pripojeni k DB, nastaveni mapperu
    |— test: obsahuje jednoduchy test overujici spravnost schematu a pripojeni k DB
pom.xml: konfigurace maven projektu, sprava zavislosti