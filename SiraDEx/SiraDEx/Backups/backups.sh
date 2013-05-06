#!/bin/bash
#=============================================================================================
# Script Backup Databases PostgreSQL - Tomeu Roig (modificado po Jorge García)
# =============================================================================================

# Procedimiento de Copia de Seguridad de Servidor de Postgres

## BEGIN CONFIG ##
BACKUP_DIR="/home/garcia/NetBeansProjects/SiraDEx/Backups/" #Dirección donde se guardaran los backups
USER="postgres"
HOST="localhost"
DB="bdsiradex"

#Realizamos la copia de seguridad de la base de datos y las guardamos en un directorio de backups

echo "* Backuping PostgreSQL data from $DB@$HOST ..."
pg_dump -i -h $HOST -U $USER --format=c -f $DB.backup $DB
echo "finalizada $DB ..."
done

