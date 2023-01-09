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

import io.airlift.slice.Slice;
import io.trino.spi.function.ScalarFunction;
import io.trino.spi.function.SqlType;
import io.trino.spi.type.StandardTypes;

public final class ObjectPersonFunctions
{
    private ObjectPersonFunctions()
    {
    }

    @ScalarFunction("ObjectPerson")
    @SqlType(ObjectPersonType.OBJECT_PERSON_TYPE_NAME)
    public static ObjectPerson toPerson(
            @SqlType(StandardTypes.VARCHAR) Slice name,
            @SqlType(StandardTypes.INTEGER) long point)
    {
        return new ObjectPerson(name.toStringUtf8(), (int) point);
    }
}
