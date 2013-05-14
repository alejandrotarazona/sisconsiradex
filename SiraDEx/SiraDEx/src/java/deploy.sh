echo "Creando variables de entorno"
echo ""

lib="Librerias/antlr-2.7.2.jar:Librerias/bsf-2.3.0.jar:Librerias/commons-beanutils-1.8.0.jar:Librerias/commons-chain-1.2.jar:Librerias/commons-digester-1.8.jar:Librerias/commons-fileupload-1.1.1.jar:Librerias/commons-io-1.1.jar:Librerias/commons-logging-1.0.4.jar:Librerias/commons-validator-1.3.1.jar:Librerias/jstl-1.0.2.jar:Librerias/oro-2.0.8.jar:Librerias/postgresql-9.0-801.jdbc3.jar:Librerias/postgresql-9.0-801.jdbc4.jar:Librerias/standard-1.0.6.jar:Librerias/struts-core-1.3.10.jar:Librerias/struts-el-1.3.10.jar:Librerias/struts-extras-1.3.10.jar:Librerias/struts-faces-1.3.10.jar:Librerias/struts-mailreader-dao-1.3.10.jar:Librerias/struts-scripting-1.3.10.jar:Librerias/struts-taglib-1.3.10.jar:Librerias/struts-tiles-1.3.10.jar"
files="Actividad/*.java Backup/*.java Catalogo/*.java Clases/*.java DBMS/*.java ElementoCatalogo/*.java TipoActividad/*.java Usuario/*.java"

echo "Variables de entorno creadas, compilando archivos"
echo ""

mkdir ../../web/WEB-INF/classes
javac -classpath $lib $files -d ../../web/WEB-INF/classes
cp -r com ../../web/WEB-INF/classes

echo "Compilacion completa, copiando librerias"
echo ""

mkdir ../../web/WEB-INF/lib
cp Librerias/*.jar ../../web/WEB-INF/lib

echo "Librerías copiadas, creando archivo .war"
echo ""

cd ../../web
jar -cf siradex.war *

echo "Archivo .war creado, limpiando carpetas temporales"
echo ""

rm -rf WEB-INF/classes
rm -rf WEB-INF/lib

echo "Desplegando aplicación"
echo ""

mv siradex.war $1/webapps

echo "Aplicación desplegada, reiniciando servidor"
echo ""

cd $1/bin
./shutdown.sh
./startup.sh

echo "Depsliegue completado"
