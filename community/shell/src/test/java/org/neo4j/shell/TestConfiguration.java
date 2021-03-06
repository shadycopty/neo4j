/**
 * Copyright (c) 2002-2013 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.shell;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.helpers.collection.MapUtil;
import org.neo4j.shell.impl.CollectingOutput;
import org.neo4j.test.ImpermanentGraphDatabase;

public class TestConfiguration
{
    private GraphDatabaseService db;
    private ShellClient client;
    
    @Before
    public void before() throws Exception
    {
        db = new ImpermanentGraphDatabase( MapUtil.stringMap( "enable_remote_shell", "true" ) );
        client = ShellLobby.newClient();
    }

    @After
    public void after() throws Exception
    {
        client.shutdown();
        db.shutdown();
    }
    
    @Test
    public void deprecatedConfigName() throws Exception
    {
        CollectingOutput output = new CollectingOutput();
        client.evaluate( "pwd", output );
        assertTrue( output.asString().contains( "(0)" ) );
    }
}
