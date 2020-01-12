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
import be.atbash.ee.security.octopus.keys.writer.KeyWriter;

import java.io.File;
import java.util.List;

public class SaveOCTJWK {

    public static void main(String[] args) {
        new File("./oct.jwk").delete();
        List<AtbashKey> keys = GenerateOCT.generateOCTKeys("demo", 512);

        KeyWriter keyWriter = new KeyWriter();
        keyWriter.writeKeyResource(keys.get(0), KeyResourceType.JWK, "./oct.jwk");
    }
}
