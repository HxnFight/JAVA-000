/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.valjean.week08.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.Properties;

/**
 * Interval sharding algorithm.
 */
public final class YMShardingAlgorithm implements StandardShardingAlgorithm<Comparable<?>> {


    @Getter
    @Setter
    private Properties props = new Properties();

    @Override
    public void init() {
    }


    private String parseDateTime(final String value) {
        return "_" + value.replaceAll("-", "").substring(0, 6);
    }

    @Override
    public String getType() {
        return "ym_sharding";
    }

    /**
     * 精确查找是走这个方法
     */

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Comparable<?>> shardingValue) {

        String suffix = parseDateTime(shardingValue.getValue().toString());
        String logicTableName = shardingValue.getLogicTableName();
        return logicTableName + suffix;
    }

    /**
     * 此方法是实现范围查找的
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Comparable<?>> rangeShardingValue) {
        return null;
    }
}
