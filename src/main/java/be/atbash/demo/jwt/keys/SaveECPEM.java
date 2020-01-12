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
import be.atbash.ee.security.octopus.keys.ListKeyManager;
import be.atbash.ee.security.octopus.keys.reader.KeyResourceType;
import be.atbash.ee.security.octopus.keys.selector.AsymmetricPart;
import be.atbash.ee.security.octopus.keys.selector.SelectorCriteria;
import be.atbash.ee.security.octopus.keys.writer.KeyWriter;
import be.atbash.ee.security.octopus.nimbus.jwk.Curve;

import java.io.File;
import java.util.List;

public class SaveECPEM {

    public static void main(String[] args) {
        new File("./ec_private.pem").delete();
        new File("./ec_public.pem").delete();
        ListKeyManager keyManager = new ListKeyManager(GenerateEC.generateECKeys("demo", Curve.P_256));

        SelectorCriteria criteria = SelectorCriteria.newBuilder().withAsymmetricPart(AsymmetricPart.PRIVATE).build();
        List<AtbashKey> privateList = keyManager.retrieveKeys(criteria);

        KeyWriter keyWriter = new KeyWriter();
        keyWriter.writeKeyResource(privateList.get(0), KeyResourceType.PEM, "./ec_private.pem", "Testing".toCharArray());

        criteria = SelectorCriteria.newBuilder().withAsymmetricPart(AsymmetricPart.PUBLIC).build();
        List<AtbashKey> publicList = keyManager.retrieveKeys(criteria);

        keyWriter.writeKeyResource(publicList.get(0), KeyResourceType.PEM, "./ec_public.pem");

    }
}
