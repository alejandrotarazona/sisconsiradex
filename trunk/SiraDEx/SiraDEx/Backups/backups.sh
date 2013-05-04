#!/bin/bash
#=============================================================================================
# Script Backup Databases PostgreSQL - Tomeu Roig (modificado po Jorge García)
# =============================================================================================

# Procedimiento de Copia de Seguridad de Servidor de Postgres

## BEGIN CONFIG ##
BACKUP_DIR="/home/garcia/NetBeansProjects/SiraDEx/Backups/" #Dirección donde se guardaran los backups
USER= garcia
FECHA=$(date +%d-%m-%Y)
HOST= localhost
DB= bdsiradex
#FECHA_BORRADO=$(date +%d-%m-%Y --date='10 days ago')
BACKUP_DIR_TODAY=$BACKUP_DIR$FECHA/
## END CONFIG ##
export PGPASSWORD= 9870087

if [ ! -d $BACKUP_DIR ]; then
mkdir -p $BACKUP_DIR
fi

echo $BACKUP_DIR_TODAY
if [ ! -d $BACKUP_DIR_TODAY ]; then
mkdir $BACKUP_DIR_TODAY
fi

#Realizamos la copia de seguridad de la base de datos y las guardamos en un directorio de backups

echo "* Backuping PostgreSQL data from $DB@$HOST ..."
pg_dump -i -h $HOST -U $USER --format=c -f $BACKUP_DIR_TODAY$DB.backup $DB

#Borramos las copias con una antiguedad mayor a 10 dias
#rm -rf $BACKUP_DIR$FECHA_BORRADO
#echo "finalizada $DB ..."
#done

