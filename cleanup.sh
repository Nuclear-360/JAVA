#!/bin/bash
# Apaga todos os recursos e comprova a exclusão
az group delete --name rg-nuclear360 --yes

# comprovação: deve imprimir false
az group exists --name rg-nuclear360
