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
import be.atbash.ee.security.octopus.keys.reader.KeyReader;

import java.util.List;

public class ReadECPEM {
    public static void main(String[] args) {
        FixedPasswordLookup lookup = new FixedPasswordLookup();
        KeyReader keyReader = new KeyReader();

        System.out.println("--------------------");
        System.out.println("-- ec_private.pem --");
        System.out.println("--------------------");
        List<AtbashKey> atbashKeys = keyReader.readKeyResource("./ec_private.pem", lookup);
        for (AtbashKey atbashKey : atbashKeys) {
            KeyInfo.showInfo(atbashKey);
        }

        System.out.println("-------------------");
        System.out.println("-- ec_public.pem --");
        System.out.println("-------------------");

        atbashKeys = keyReader.readKeyResource("./ec_public.pem");
        for (AtbashKey atbashKey : atbashKeys) {
            KeyInfo.showInfo(atbashKey);
        }

    }
}
