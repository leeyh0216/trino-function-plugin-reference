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
package com.leeyh0216.trino.udf.lambda;

import io.airlift.slice.Slice;
import io.trino.spi.block.Block;
import io.trino.spi.block.BlockBuilder;
import io.trino.spi.function.ScalarFunction;
import io.trino.spi.function.SqlType;
import io.trino.spi.function.TypeParameter;
import io.trino.spi.function.TypeParameterSpecialization;
import io.trino.spi.type.Type;
import io.trino.sql.gen.lambda.UnaryFunctionInterface;

@ScalarFunction("sample_unary_lambda")
public final class SampleUnaryLambdaFunction
{
    private SampleUnaryLambdaFunction()
    {
        //Prevent create class
    }

    @TypeParameter("T")
    @TypeParameter("U")
    @TypeParameterSpecialization(name = "T", nativeContainerType = long.class)
    @SqlType("array(U)")
    public static Block sampleUnaryLambdaLong(
            @TypeParameter("T") Type elementType,
            @TypeParameter("U") Type resultType,
            @SqlType("array(T)") Block array,
            @SqlType("function(T, U)") UnaryFunctionInterface lambdaFunction)
    {
        int sizeOfArray = array.getPositionCount();
        BlockBuilder resultBuilder = resultType.createBlockBuilder(null, sizeOfArray);
        for (int i = 0; i < sizeOfArray; i++) {
            Long input = null;
            if (!array.isNull(i)) {
                input = elementType.getLong(array, i);
            }

            writeResult(resultType, resultBuilder, lambdaFunction.apply(input));
        }

        return resultBuilder.build();
    }

    @TypeParameter("T")
    @TypeParameter("U")
    @TypeParameterSpecialization(name = "T", nativeContainerType = double.class)
    @SqlType("array(U)")
    public static Block sampleUnaryLambdaDouble(
            @TypeParameter("T") Type elementType,
            @TypeParameter("U") Type resultType,
            @SqlType("array(T)") Block array,
            @SqlType("function(T, U)") UnaryFunctionInterface lambdaFunction)
    {
        int sizeOfArray = array.getPositionCount();
        BlockBuilder resultBuilder = resultType.createBlockBuilder(null, sizeOfArray);
        for (int i = 0; i < sizeOfArray; i++) {
            Double input = null;
            if (!array.isNull(i)) {
                input = elementType.getDouble(array, i);
            }

            writeResult(resultType, resultBuilder, lambdaFunction.apply(input));
        }

        return resultBuilder.build();
    }

    @TypeParameter("T")
    @TypeParameter("U")
    @TypeParameterSpecialization(name = "T", nativeContainerType = boolean.class)
    @SqlType("array(U)")
    public static Block sampleUnaryLambdaBoolean(
            @TypeParameter("T") Type elementType,
            @TypeParameter("U") Type resultType,
            @SqlType("array(T)") Block array,
            @SqlType("function(T, U)") UnaryFunctionInterface lambdaFunction)
    {
        int sizeOfArray = array.getPositionCount();
        BlockBuilder resultBuilder = resultType.createBlockBuilder(null, sizeOfArray);
        for (int i = 0; i < sizeOfArray; i++) {
            Boolean input = null;
            if (!array.isNull(i)) {
                input = elementType.getBoolean(array, i);
            }

            writeResult(resultType, resultBuilder, lambdaFunction.apply(input));
        }

        return resultBuilder.build();
    }

    @TypeParameter("T")
    @TypeParameter("U")
    @TypeParameterSpecialization(name = "T", nativeContainerType = Object.class)
    @SqlType("array(U)")
    public static Block sampleUnaryLambdaObject(
            @TypeParameter("T") Type elementType,
            @TypeParameter("U") Type resultType,
            @SqlType("array(T)") Block array,
            @SqlType("function(T, U)") UnaryFunctionInterface lambdaFunction)
    {
        int sizeOfArray = array.getPositionCount();
        BlockBuilder resultBuilder = resultType.createBlockBuilder(null, sizeOfArray);
        for (int i = 0; i < sizeOfArray; i++) {
            Object input = null;
            if (!array.isNull(i)) {
                input = elementType.getObject(array, i);
            }

            writeResult(resultType, resultBuilder, lambdaFunction.apply(input));
        }

        return resultBuilder.build();
    }

    private static void writeResult(Type resultType, BlockBuilder resultBuilder, Object objectToWrite)
    {
        if (objectToWrite == null) {
            resultBuilder.appendNull();
            return;
        }

        Class<?> resultTypeClass = resultType.getJavaType();
        if (resultTypeClass == long.class) {
            resultType.writeLong(resultBuilder, (long) objectToWrite);
        }
        else if (resultTypeClass == double.class) {
            resultType.writeDouble(resultBuilder, (double) objectToWrite);
        }
        else if (resultTypeClass == boolean.class) {
            resultType.writeBoolean(resultBuilder, (boolean) objectToWrite);
        }
        else if (resultTypeClass == Slice.class) {
            resultType.writeSlice(resultBuilder, (Slice) objectToWrite);
        }
        else {
            resultType.writeObject(resultBuilder, objectToWrite);
        }
    }
}
