/*
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
package com.leeyh0216.trino.udf.type.object;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ObjectPerson
{
    private final String name;
    private final int point;

    public ObjectPerson(String name, int point)
    {
        this.name = name;
        this.point = point;
    }

    @JsonProperty("name")
    public String getName()
    {
        return name;
    }

    @JsonProperty("point")
    public int getPoint()
    {
        return point;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ObjectPerson objectPerson = (ObjectPerson) o;
        return point == objectPerson.point && Objects.equals(name, objectPerson.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, point);
    }
}
