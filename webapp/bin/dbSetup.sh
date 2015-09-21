#!/usr/bin/env bash
#
# This script initializes the database for the project.

# Created by peter mwenda, <peter@gmail.com> on july 09 2015

# Some notes on PostgreSQL:
# By default, PostgreSQL has a database called template1 containing privileges 
# and other housekeeping data and an adminstrative user named postgres. Unless 
# your system uses socket credentials, the postgres database user initially has 
# no password. To assign it a password (or to override the password assigned by 
# socket credentials), do as follows:
#
# * Become the user "postgres" by becoming superuser then "su - postgres"
# * Execute the following command:
# $> psql -c "ALTER USER postgres WITH PASSWORD 'newpassword'" -d template1
# The command above assigns the postgres user the password 'newpassword'.

#If you are creating the role and the database for the first time, then 
#you need to enable the lines below.
#Use these lines to automatically create a role in production environment 

#Begin automatic creation of role
DB_USERNAME="postgres"
DB_PASSWORD="root"
DB_HOST="localhost"

export PGUSER=$DB_USERNAME
export PGHOST=$DB_HOST
export PGPASSWORD=$DB_PASSWORD

echo "Creating new role..."

#If a user has not been created, uncomment the line below
#psql -c "CREATE USER sema WITH PASSWORD 'g6kp7lwt' CREATEDB"

echo "Finished creating new role."
#End automatic creation of role

# Initialize the following variables as appropriate:
DB_USERNAME="school"
DB_PASSWORD="AllaManO1"
DB_HOST="localhost"

# There should be no need to change anything below this line.
echo "Starting database initialization script..."

export PGUSER=$DB_USERNAME
export PGHOST=$DB_HOST
export PGPASSWORD=$DB_PASSWORD

# Copy data to be imported into the Linux temporary folder
cp -fr ../etc/sql/data/* /tmp

psql -f ../etc/sql/tables.postgres.sql -d postgres

#psql -f ../etc/sql/tables.quartz.sql -d postgres

echo "Have finished initializing database."

# vim:tabstop=2:expandtab:shiftwidth=2
