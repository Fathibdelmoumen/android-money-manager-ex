/*
 * Copyright (C) 2012-2015 The Android Money Manager Ex Project Team
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.moneymanagerex.android.tests;

import com.money.manager.ex.BuildConfig;
import com.money.manager.ex.home.RecentDatabaseEntry;
import com.money.manager.ex.home.RecentDatabasesProvider;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.Queue;

/**
 * Tests for Recent Database Provider.
 *
 * Created by Alen Siljak on 20/08/2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class RecentDatabaseProviderTests {

    private RecentDatabasesProvider _testObject;

    @Before
    public void setUp() throws Exception {
//        MockContext context = new MockContext();
        _testObject = new RecentDatabasesProvider(RuntimeEnvironment.application);
    }

    @After
    public void tearDown() throws Exception {
        _testObject = null;
    }

    @Test
    public void testLoad() throws Exception {
        Queue<RecentDatabaseEntry> expected = getQueue();

        LinkedHashMap<String, RecentDatabaseEntry> actual = _testObject.map;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSave() {
//        Queue<RecentDatabaseEntry> queue = getQueue();
        String expected = "todo";

        _testObject.save();

        String actual = _testObject.readPreference();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testInsert() {
        RecentDatabaseEntry entry = getEntry();
        String expected = "[{\"filePath\":\"filename.mmb\",\"dropboxFileName\":\"\",\"linkedToDropbox\":false}]";

        _testObject.add(entry);

        String actual = _testObject.toJson();

        Assert.assertEquals(expected, actual);
    }

    // Private

    private Queue<RecentDatabaseEntry> getQueue() {
        Queue<RecentDatabaseEntry> list = new ArrayDeque<>();

        list.add(getEntry());
        list.add(getEntry());
        list.add(getEntry());

        return list;
    }

    private RecentDatabaseEntry getEntry() {
        RecentDatabaseEntry entry = new RecentDatabaseEntry();

        entry.dropboxFileName = "";
        entry.linkedToDropbox = false;
        entry.filePath = "filename.mmb";

        return entry;
    }

}