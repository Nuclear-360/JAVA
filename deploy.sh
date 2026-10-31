#!/bin/bash
# Nuclear 360 - provisiona a VM no Azure e sobe a API (H2 embarcado, 1 container)
set -e

RG=rg-nuclear360
LOC=brazilsouth
VM=vm-nuclear360
REPO=https://github.com/Nuclear-360/JAVA.git
MEU_IP=$(curl -s ifconfig.me)

# 1. grupo de recursos (a "caixa" que junta tudo; permite apagar com um comando)
az group create --name $RG --location $LOC

# 2. VM Ubuntu, sem nenhuma porta aberta por padrão
az vm create --resource-group $RG --name $VM \
  --image Ubuntu2204 --size Standard_D2s_v4 \
  --admin-username azureuser --generate-ssh-keys \
  --nsg-rule NONE

# 3. libera a porta 22 (SSH) só para o SEU IP
az network nsg rule create -g $RG --nsg-name ${VM}NSG -n ssh-meu-ip \
  --priority 100 --protocol Tcp --access Allow \
  --source-address-prefixes $MEU_IP --destination-port-ranges 22

# 4. libera a porta 8080 (a API) para o mundo
az network nsg rule create -g $RG --nsg-name ${VM}NSG -n api-http \
  --priority 110 --protocol Tcp --access Allow \
  --destination-port-ranges 8080

# 5. instala o Docker na VM, clona o repo, builda a imagem e sobe o container
az vm run-command invoke -g $RG -n $VM --command-id RunShellScript --scripts \
  "curl -fsSL https://get.docker.com | sh && \
   cd /home/azureuser && git clone $REPO app && cd app && \
   docker build -t nuclear360-api . && \
   docker run -d --restart unless-stopped -p 8080:8080 --name api nuclear360-api && \
   docker ps"

# 6. mostra o IP público para acessar a API
echo "=== IP público da VM (acesse http://IP:8080/api/pergunta) ==="
az vm show -d -g $RG -n $VM --query publicIps -o tsv
