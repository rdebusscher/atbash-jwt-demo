# Atbash JWT demo

The JWT Support project of Atbash has following goals

- Uniform API for generating cryptographic keys.
- Uniform API for reading and writing cryptographic keys from and to different formats.
- Support for JWT tokens.

## Atbash Keys

Atbash Keys wrap a cryptograhic key and contain metadata like an identification and type information like RSA, Private key, etc ...

## Creating Atbash Keys

Creating Atbash Keys can be done using the `be.atbash.ee.security.octopus.keys.generator.KeyGenerator` class.

Based on the `be.atbash.ee.security.octopus.keys.generator.GenerationParameters` data as parameter, the method `generateKeys()` generates one key or a keyPair.

The following classes shows you how you can generate the different type of keys.

- RSA keys : `be.atbash.demo.jwt.keys.GenerateRSA`
- Elliptic Curve keys : `be.atbash.demo.jwt.keys.GenerateEC`
- Symmetric keys : `be.atbash.demo.jwt.keys.GenerateOCT`
- Edwards-curve keys : `be.atbash.demo.jwt.keys.GenerateOKP`

## Writing to JWK format

Cryptographic keys can be written to the JWK (Json Web Key) format through an instance of `be.atbash.ee.security.octopus.keys.writer.KeyWriter`.

This format doesn't encrypt content which can be an issue (except for the Public type of keys).

An example for writing keys can be found in the classes

- RSA keys : `be.atbash.demo.jwt.keys.SaveRSAJWK`
- Elliptic Curve keys : `be.atbash.demo.jwt.keys.SaveECJWK`
- Symmetric keys : `be.atbash.demo.jwt.keys.SaveOCTJWK`
- Edwards-curve keys : `be.atbash.demo.jwt.keys.SaveOKPJWK`

## Writing to JWKSet format

The JWKSet format is similar to JWK but contains multiple keys. When you write to an already existing file, the key is appended to the file. This is in contrast with the other writing formats where the file can't be overwritten by the Atbash code.

An example for writing keys can be found in the class `be.atbash.demo.jwt.keys.SaveJWKSet`.
 
## Writing to PEM format

The PEM format, Privacy Enhanced Mail format, is nowadays a very wide spread format (the de facto standard) used in a lot of languages, not only JAR. It is The BASE64 encoded DER format for the key.
DER (Distinguished Encoding Rules ) in his turn, is the binary representation of the ASN.1 format used in many cryptographic standards.

By default, Atbash encoded the private keys when storing the in the PEM format using the PKCS #8 standard. Therefor, a password (or also called passphrase) is required when writing this type of files.

Symmetric keys can't be stored as PEM format.

An example for writing keys can be found in the classes

- RSA keys : `be.atbash.demo.jwt.keys.SaveRSAPEM`
- Elliptic Curve keys : `be.atbash.demo.jwt.keys.SaveECPEM`
- Edwards-curve keys : `be.atbash.demo.jwt.keys.SaveOKPPEM`

## Writing to Java KeyStore

The Java Keystore has a Java specific format (JKS) and a more generic format, PKCS#12. This format requires encoding of the private keys and the store as a whole. By default the format is PKCS#12.  
Storing public keys are done as a X.509 Certificate.
 
An example for writing keys can be found in the classes

- RSA keys : `be.atbash.demo.jwt.keys.SaveRSAKeyStore`
- EC keys : `be.atbash.demo.jwt.keys.SaveECKeyStore`

## Reading JWK File

The framework support reading the cryptographic keys which are stored in the JWK format.

An example for reading keys can be found in the classes

- RSA keys : `be.atbash.demo.jwt.keys.ReadRSAJWK`
- Elliptic Curve keys : `be.atbash.demo.jwt.keys.ReadECJWK`
- Symmetric keys : `be.atbash.demo.jwt.keys.ReadOCTJWK`
- Edwards-curve keys : `be.atbash.demo.jwt.keys.ReadOKPJWK`

## Reading JWKSet File

The framework support reading the cryptographic keys which are stored in the JWKSet format.

An example of reading this format is given in the class be.atbash.demo.jwt.keys.ReadJWKSet`.

## Reading PEM File

The framework support reading the cryptographic keys which are stored in the PEM format.

An example for reading keys (PKCS #8 encoded private keys or public keys) can be found in the classes

- RSA keys : `be.atbash.demo.jwt.keys.ReadRSAPEM`
- Elliptic Curve keys : `be.atbash.demo.jwt.keys.ReadECPEM`
- Edwards-curve keys : `be.atbash.demo.jwt.keys.ReadOKPPEM`

## Reading Java KeyStore files

- RSA keys : `be.atbash.demo.jwt.keys.ReadRSAKeyStore`
- EC keys : `be.atbash.demo.jwt.keys.ReadECKeyStore`

# Advanced

## PEM encryption configuration

## Custom suffix to Type

KeyResourceTypeProvider