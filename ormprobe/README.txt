Struktura projektu:

java - obsahuje zdrojové kódy v tìchto balíkách:

	dao - obsahuje implementaci dotazù pomocí Query a Criteria
	domain - obsahuje objekty a jejich mapování
	servlet - obsahuje implementaci servletu obsluhujícího dotazy a servletu obsluhujícího generátor
	utils - obsahuje tøídy pro práci s generátorem dat a pøipojením

resources - obsahuje soubor konfigurující pøipojení a 
	seznam mapovaných tøíd

webapp - obsahuje jsp stránku s formuláøem, context 
	v META-INF a ve WEB-INF web.xml s nastavením
	servletù