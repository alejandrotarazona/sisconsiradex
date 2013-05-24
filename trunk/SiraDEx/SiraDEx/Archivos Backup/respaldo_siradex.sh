#!/bin/bash
#=============================================================================================
# Script Backup Databases PostgreSQL - Tomeu Roig (modificado po Jorge García)      
#=============================================================================================

USER="postgres"
HOST="localhost"
DB="bdsiradex"
FH=`date '+_%d_%m_%Y'`
budate=`date '+%Y%m%d'`
path='/home/backups_siradex/'
i=0
while read line 
do 
	 eval array$i='$line'
	 i=$(($i+1))
done < $path\frecuencia.txt

if [ "$array0" -ne "0" ]; then 

d1=`date -d $budate +%s`
d2=`date -d "$array1" +%s`        

dif=$((($d1-$d2)/86400))

	if [ "$dif" -ge "$array0" ]; then

		  #Realiza la copia de seguridad de la base de datos

			echo "* Creando backup de datos PostgreSQL desde $DB@$HOST ..."
			pg_dump -i -h $HOST -U $USER --format=c -f $path$DB$FH.backup $DB
			echo "finalizada copia de $DB ..."

			echo "* Creando zip con el backup y los archivos necesarios para la gestión del backup ..."
			zip $path\respaldo_siradex$FH $path$DB$FH.backup
			zip $path\respaldo_siradex$FH /etc/crontab
      zip $path\respaldo_siradex$FH $path\frecuencia.txt
      zip $path\respaldo_siradex$FH $path\respaldo_siradex.sh
			echo "finalizada creación de $path$DB$FH.backup ..."

			uuencode $path\respaldo_siradex$FH.zip respaldo_siradex$FH.zip | mail -s "Respaldo SiraDEx (zip que incluye un respaldo base de datos y archivos necesarios para la gestión del backup)" $array2

	fi

fi

echo -e "$array0\n$budate\n$array2" > $path\frecuencia.txt




