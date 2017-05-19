/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.baeldung.spring.data.cassandra.model;

import lombok.Data;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;


@Table
@Data
public class FlatGroup {

	@PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.PARTITIONED) private String groupname;
	@PrimaryKeyColumn(name = "hash_prefix", ordinal = 2, type = PrimaryKeyType.PARTITIONED) private String hashPrefix;
	@PrimaryKeyColumn(ordinal = 3, type = PrimaryKeyType.CLUSTERED) private String username;

	private String email;
	private int age;

	public FlatGroup(String groupname, String hashPrefix, String username) {
		this.groupname = groupname;
		this.hashPrefix = hashPrefix;
		this.username = username;
	}
}
