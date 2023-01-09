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
import io.trino.spi.block.Block;
import io.trino.spi.block.BlockBuilder;
import io.trino.spi.type.VarcharType;

public final class ObjectPersonSerde
{
    private static final int NAME_POSITION = 0;
    private static final int POINT_POSITION = 1;

    private ObjectPersonSerde()
    {
    }

    static ObjectPerson deserialize(Block block)
    {
        String name = ((Slice) VarcharType.VARCHAR.getObject(block, NAME_POSITION)).toStringUtf8();
        int point = block.getInt(POINT_POSITION, 0);
        return new ObjectPerson(name, point);
    }

    static void serialize(BlockBuilder blockBuilder, ObjectPerson objectPerson)
    {
        BlockBuilder rowBlockBuilder = blockBuilder.beginBlockEntry();
        VarcharType.VARCHAR.writeString(rowBlockBuilder, objectPerson.getName());
        rowBlockBuilder.writeInt(objectPerson.getPoint());
        blockBuilder.closeEntry();
    }
}
