src
|— main
    |— java
        |— com.danekja.edu.ormprobe
            |— controllers: Slozka obsahuje Spring controllery (TestDaoController
                            volající metody TestDao, GroupsController pouzity pri
                            testovani spojeni s DB a MainController, ktery slouzi
                            k obsluze indexu a generovani dat)
            |— dao: obsahuje pouze zadane rozhrani TestDao
            |— domain: Java beans popisujici model aplikace
            |— persistance: obsahuje rozhrani s prototypy metod, ktere jsou mapovany
                            na SQL dotazy
            |— service: jednoduche tridy volajici metody z rozhrani definovanych v persistance
            |— utils: obsahuje rozhrani DatabasePersistUtil, ktere definuje metody potrebne
                        pro ulozeni dat vytvorenych generatorem; dale je ze samotny generator
                        a implementace DatabasePersistUtil pro MyBatis
    |— resources
        |— com.danekja.edu.ormprobe
            |— persistance: XML soubory obsahujici mapovani metod na SQL
        |— database: obsahuje SQL skript pro inicializaci schematu/databaze
    |— webapp
        |— WEBINF: obsahuje applicationContext, v nemz je konfigurace Springu
                    a databazoveho pripojeni; dale je zde index.jsp, jediny JSP soubor aplikace
                    a web.xml s nastavenim servlet dispatcheru
    |— test: obsahuje jednoduchy test overujici spravnost schematu a pripojeni k DB
pom.xml: konfigurace maven projektu, sprava zavislosti