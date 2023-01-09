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

import io.trino.spi.block.Block;
import io.trino.spi.block.BlockBuilder;
import io.trino.type.AbstractTestType;

public class TestObjectObjectPersonType
        extends AbstractTestType
{
    protected TestObjectObjectPersonType()
    {
        super(ObjectPersonType.OBJECT_PERSON, ObjectPerson.class, createTestBlock());
    }

    private static Block createTestBlock()
    {
        BlockBuilder blockBuilder = ObjectPersonType.OBJECT_PERSON.createBlockBuilder(null, 2);
        ObjectPerson objectPerson = new ObjectPerson("leeyh0216", 1);
        ObjectPersonType.OBJECT_PERSON.writeObject(blockBuilder, objectPerson);
        return blockBuilder.build();
    }

    @Override
    protected Object getGreaterValue(Object value)
    {
        return null;
    }
}
