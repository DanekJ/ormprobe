src
|— main
    |— java
        |— com.danekja.edu.ormprobe
            |— dao: obsahuje zadane rozhrani TestDao a jeho dvě implementace (HQL a Critery Query)
            |— domain: Java beans popisujici model aplikace
            |— servlets: obsahuje servlety pro webovou aplikaci
            |— utils: obsahuje rozhrani DatabasePersistUtil, ktere definuje metody potrebne
                pro ulozeni dat vytvorenych generatorem; dale je ze samotny generator
                a implementace JPAPersistUtil pro Hibernate
    |— resources
        |— com.danekja.edu.ormprobe
            |— persistance: XML soubor, ktery obsahuje nastaveni pripojeni k DB
        |— jdbc.properties: obsahuje nastaveni pripojeni k DB

    |- webapp: obsahuje index.jsp
        |- WEB-INF: obsahuje web.xml a menuView.jsp


pom.xml: konfigurace maven projektu, sprava zavislosti