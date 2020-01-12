/*
 * Copyright 2020 Rudy De Busscher (https://www.atbash.be)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.atbash.demo.jwt.keys;

import be.atbash.ee.security.octopus.keys.AtbashKey;
import be.atbash.ee.security.octopus.keys.reader.KeyResourceType;
import be.atbash.ee.security.octopus.keys.selector.AsymmetricPart;
import be.atbash.ee.security.octopus.keys.writer.KeyWriter;
import be.atbash.ee.security.octopus.nimbus.jwk.Curve;

import java.io.File;
import java.util.List;

public class SaveJWKSet {

    public static void main(String[] args) {
        new File("./demo.jwks").delete();
        List<AtbashKey> keys = GenerateRSA.generateRSAKeys("demo_rsa");
        keys.addAll(GenerateEC.generateECKeys("demo_ec", Curve.P_256));
        keys.addAll(GenerateOCT.generateOCTKeys("demo_oct"));
        keys.addAll(GenerateOKP.generateOKPKeys("demo_okp"));

        KeyWriter keyWriter = new KeyWriter();
        keys.forEach(key -> {
                    if (key.getSecretKeyType().getAsymmetricPart() != AsymmetricPart.PUBLIC) {
                        // Do not write public keys here as otherwise we have double ids
                        // And when reading a JWK, the public key is extracted.
                        keyWriter.writeKeyResource(key, KeyResourceType.JWKSET, "./demo.jwks");
                    }
                }
        );

    }
}
