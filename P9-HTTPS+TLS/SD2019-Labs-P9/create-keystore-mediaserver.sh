keytool -genkey -alias server1 -keyalg RSA -validity 365 -keystore mediaserver.ks -storetype pkcs12
keytool -exportcert -alias server1 -keystore mediaserver.ks -file mediaserver.cert
