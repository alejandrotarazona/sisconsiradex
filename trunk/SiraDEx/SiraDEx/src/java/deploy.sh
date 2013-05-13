echo "Creando variables de entorno"
echo ""

files="Actividad/*.java Backup/*.java Catalogo/*.java Clases/*.java DBMS/*.java DBMS/*.sql ElementoCatalogo/*.java TipoActividad/*.java Usuario/*.java Usuarios/*.java"

echo "Variables de entorno creadas, compilando archivos"
echo ""

mkdir ../../web/WEB-INF/classes
javac -classpath $files -d ../../web/WEB-INF/classes
cp -r com ../../web/WEB-INF/classes

echo "Compilacion completa, copiando librerias"
echo ""

mkdir ../../web/WEB-INF/lib
cp Libraries/*.jar ../../web/WEB-INF/lib

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
