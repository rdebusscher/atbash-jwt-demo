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
import be.atbash.ee.security.octopus.keys.reader.password.KeyResourcePasswordLookup;

import java.util.List;

public class ReadECKeyStore {
    public static void main(String[] args) {
        KeyReader keyReader = new KeyReader();

        System.out.println("---------------------");
        System.out.println("-- ec_private.p12 --");
        System.out.println("---------------------");
        List<AtbashKey> atbashKeys = keyReader.readKeyResource("./ec_private.p12", new TestPassworkLookup());
        for (AtbashKey atbashKey : atbashKeys) {
            KeyInfo.showInfo(atbashKey);
        }


        System.out.println("--------------------");
        System.out.println("-- ec_public.p12 --");
        System.out.println("--------------------");

        atbashKeys = keyReader.readKeyResource("./ec_public.p12", new TestPassworkLookup());
        for (AtbashKey atbashKey : atbashKeys) {
            KeyInfo.showInfo(atbashKey);
        }



    }

    public static class TestPassworkLookup implements KeyResourcePasswordLookup {

        @Override
        public char[] getResourcePassword(String s) {
            return "ecFilePassphrase".toCharArray();
        }

        @Override
        public char[] getKeyPassword(String s, String s1) {
            return "ecKeyPassphrase".toCharArray();
        }
    }
}
