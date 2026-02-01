# A0 Tests

Use the table below to describe your test cases. Each row should describe one test.

| Test name | Setup (steps) | Operation | Expected result | Exception? |
| --- | --- | --- | --- | --- |
| size on empty list | create empty list | size() | 0 | n/a |
| size on non-empty list | create empty list, add two items | size() | 2 | n/a |
| isEmpty on empty list | create empty list | isEmpty() | true | n/a |
| --- | --- | --- | --- | --- |
| isEmpty on non-empty list | create empty list, add an item | isEmpty() | false | n/a |
| --- | --- | --- | --- | --- |
| get on empty list | create empty list | get(0) | n/a | IndexOutOfBoundsException |
| --- | --- | --- | --- | --- |
| get with negative index | create empty list, add an item | get(-1) | n/a | IndexOutOfBoundsException |
| --- | --- | --- | --- | --- |
| get with index too high | create empty list, add an item | get(1) | n/a | IndexOutOfBoundsException |
| --- | --- | --- | --- | --- |
| get with index 0 | create empty list, add three items | get(0) | first item | n/a |
| --- | --- | --- | --- | --- |
| get with middle index | create empty list, add three items | get(1) | middle item | n/a |
| --- | --- | --- | --- | --- |
| get with max valid index | create empty list, add three items | get(2) | last item | n/a |
| --- | --- | --- | --- | --- |
| insert on empty list | create empty list, initialize item | insert(item, 0), get(0) | the item | n/a |
| --- | --- | --- | --- | --- |
| insert with negative index | create empty list, initialize item | insert(item, -1) | n/a | IndexOutOfBoundsException |
| --- | --- | --- | --- | --- |
| insert with index too high | create empty list, initialize item | insert(item, 1) | n/a | IndexOutOfBoundsException |
| --- | --- | --- | --- | --- |
| insert with index 0 | create empty list, add an item, initialize another item | insert(item, 0), get(0), get(1) | get(0) returns newly added item, get(1) returns the other | n/a |
| --- | --- | --- | --- | --- |
| --- | --- | --- | --- | --- |
| --- | --- | --- | --- | --- |
| --- | --- | --- | --- | --- |
| --- | --- | --- | --- | --- |
| --- | --- | --- | --- | --- |