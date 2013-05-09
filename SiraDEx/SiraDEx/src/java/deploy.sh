echo "Creando variables de entorno"
echo ""

cp="Librerias/itextpdf-5.1.1.jar:Librerias/javax.mail-1.3.3.01.jar:Librerias/jxl.jar:Librerias/postgresql-9.0-801.jdbc3.jar:Librerias/struts-taglib-1.3.10.jar:Librerias/postgresql-9.0-801.jdbc4.jar:Librerias/servlet-2.3.jar:Librerias/struts-core-1.3.10.jar:Librerias/struts-el-1.3.10.jar:Librerias/struts-tiles-1.3.10.jar:Librerias/struts-extras-1.3.10.jar:Librerias/struts-faces-1.3.10.jar:Librerias/struts-mailreader-dao-1.3.10.jar:Librerias/struts-scripting-1.3.10.jar"

files="Negocio/*.java filters/*.java clases/*.java DBMS/*.java pio/action/*.java pio/action/administrador/aulas/*.java pio/action/administrador/inscripcion/*.java pio/action/inscrito/*.java pio/action/administrador/paradas/*.java pio/action/administrador/preinscripcion/*.java pio/action/administrador/carreras/*.java pio/action/administrador/rutas/*.java pio/action/visitante/*.java pio/action/administrador/coordinadores/*.java pio/action/administrador/preseleccion/*.java pio/action/administrador/control/*.java pio/action/administrador/instituciones/*.java pio/action/administrador/noticias/*.java pio/action/administrador/solicitudes/*.java pio/action/administrador/resultados/*.java pio/action/administrador/*.java pio/action/aspirante/*.java pio/action/coordinador/*.java pio/action/preinscrito/*.java"

echo "Variables de entorno creadas, compilando archivos"
echo ""

mkdir ../../web/WEB-INF/classes
javac -classpath $cp $files -d ../../web/WEB-INF/classes
cp -r com ../../web/WEB-INF/classes

echo "Compilacion completa, copiando librerias externas"
echo ""

mkdir ../../web/WEB-INF/lib
cp Librerias/*.jar ../../web/WEB-INF/lib

echo "Librerías copiadas, creando archivo .war"
echo ""

cd ../../web
jar -cf sigpio.war *

echo "Archivo .war creado, limpiando carpetas temporales"
echo ""

rm -rf WEB-INF/classes
rm -rf WEB-INF/lib

echo "Desplegando aplicación"
echo ""

mv sigpio.war $1/webapps

echo "Aplicación desplegada, reiniciando servidor"
echo ""

cd $1/bin
./shutdown.sh
./startup.sh

echo "Depsliegue completado"
