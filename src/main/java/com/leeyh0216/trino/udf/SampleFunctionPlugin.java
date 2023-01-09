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
package com.leeyh0216.trino.udf;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.leeyh0216.trino.udf.lambda.SampleUnaryLambdaFunction;
import com.leeyh0216.trino.udf.type.object.ObjectPersonFunctions;
import com.leeyh0216.trino.udf.type.object.ObjectPersonType;
import io.trino.spi.Plugin;
import io.trino.spi.type.Type;

import java.util.Set;

public class SampleFunctionPlugin
        implements Plugin
{
    @Override
    public Iterable<Type> getTypes()
    {
        return ImmutableList.of(ObjectPersonType.OBJECT_PERSON);
    }

    @Override
    public Set<Class<?>> getFunctions()
    {
        return ImmutableSet.<Class<?>>builder().add(SampleUnaryLambdaFunction.class, ObjectPersonFunctions.class).build();
    }
}
