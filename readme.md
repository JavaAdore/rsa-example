# Intro
just sample implementation of using RSA encrypting
# To generate keypairs
install openssl and add it into your class path

# then go to resources folder
(terminal-cmd)>openssl genrsa -out test.pem 1024
# to extract private key from test.pem
(terminal-cmd)>openssl pkcs8 -topk8 -in test-private-key.pem -inform pem -out private_key_pkcs8.pem -outform pem -nocrypt
# to extract public key from test.pem
(terminal-cmd)> openssl rsa -pubout -in test.pem -out test-public-key.pem

