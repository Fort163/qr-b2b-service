#!/bin/bash


psql -U postgres -tc "SELECT 1 FROM pg_database WHERE datname = 'qr_b2b'" | grep -q 1 || psql -U postgres -c "CREATE DATABASE qr_b2b"

psql -U postgres -tc "SELECT 1 FROM pg_database WHERE datname = 'qr_b2b_test'" | grep -q 1 || psql -U postgres -c "CREATE DATABASE qr_b2b_test"
