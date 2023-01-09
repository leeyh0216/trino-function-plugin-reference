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

import com.google.common.collect.ImmutableList;
import io.trino.spi.block.Block;
import io.trino.spi.block.BlockBuilder;
import io.trino.spi.block.BlockBuilderStatus;
import io.trino.spi.block.RowBlockBuilder;
import io.trino.spi.connector.ConnectorSession;
import io.trino.spi.type.AbstractVariableWidthType;
import io.trino.spi.type.IntegerType;
import io.trino.spi.type.TypeSignature;
import io.trino.spi.type.VarcharType;

public class ObjectPersonType
        extends AbstractVariableWidthType
{
    public static final ObjectPersonType OBJECT_PERSON = new ObjectPersonType();
    public static final String OBJECT_PERSON_TYPE_NAME = "ObjectPerson";

    private ObjectPersonType()
    {
        super(new TypeSignature(OBJECT_PERSON_TYPE_NAME), Object.class);
    }

    protected ObjectPersonType(TypeSignature signature)
    {
        super(signature, Object.class);
    }

    @Override
    public Object getObjectValue(ConnectorSession session, Block block, int position)
    {
        if (block.isNull(position)) {
            return null;
        }
        return getObject(block, position);
    }

    @Override
    public void appendTo(Block block, int position, BlockBuilder blockBuilder)
    {
        if (block.isNull(position)) {
            blockBuilder.appendNull();
        }
        else {
            writeObject(blockBuilder, getObject(block, position));
        }
    }

    @Override
    public Object getObject(Block block, int position)
    {
        if (block.isNull(position)) {
            return null;
        }
        return ObjectPersonSerde.deserialize(block.getObject(position, Block.class));
    }

    @Override
    public void writeObject(BlockBuilder blockBuilder, Object value)
    {
        ObjectPersonSerde.serialize(blockBuilder, (ObjectPerson) value);
    }

    @Override
    public BlockBuilder createBlockBuilder(BlockBuilderStatus blockBuilderStatus, int expectedEntries, int expectedBytesPerEntry)
    {
        return new RowBlockBuilder(ImmutableList.of(VarcharType.VARCHAR, IntegerType.INTEGER), blockBuilderStatus, expectedEntries);
    }

    @Override
    public BlockBuilder createBlockBuilder(BlockBuilderStatus blockBuilderStatus, int expectedEntries)
    {
        return createBlockBuilder(blockBuilderStatus, expectedEntries, 1000);
    }
}
