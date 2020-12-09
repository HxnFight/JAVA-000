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

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Properties;

/**
 * Interval sharding algorithm.
 */
public final class YMShardingAlgorithm implements StandardShardingAlgorithm<String> {

    @Override
    public void init() {
    }

    /**
     * 精确查找是走这个方法
     *
     * @param availableTargetNames
     * @param shardingValue
     * @return
     */
    @Override
    public String doSharding(final Collection<String> availableTargetNames, final PreciseShardingValue<String> shardingValue) {

        String suffix = parseDateTime(shardingValue.getValue().toString()).format(DateTimeFormatter.ofPattern("yyyyMM"));
        String logicTableName = shardingValue.getLogicTableName();
        return logicTableName + suffix;
    }


    /**
     * 此方法是实现范围查找的
     *
     * @param availableTargetNames
     * @param shardingValue
     * @return
     */
    @Override
    public Collection<String> doSharding(final Collection<String> availableTargetNames,
                                         final RangeShardingValue<String> shardingValue) {
        return null;
    }

    private LocalDateTime parseDateTime(final String value) {
        return LocalDateTime.parse(value.substring(0, 6), DateTimeFormatter.ofPattern("yyyyMM"));
    }


    @Override
    public String getType() {
        return "CLASS_BASED";
    }

    @Override
    public Properties getProps() {
        return null;
    }

    @Override
    public void setProps(Properties properties) {

    }
}
