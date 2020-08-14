// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.lib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class GreetingsTest {

  @Test
  public void getGreetings_greetingIsReadFromDatabase() {
    MockDatabaseReader mockDatabaseReader = new MockDatabaseReader();
    mockDatabaseReader.addMessage("Jintreting", "theGarage");

    String greeting = new Greetings(mockDatabaseReader).getGreetings("Jintreting");

    assertEquals("theGarage", greeting);
  }
}