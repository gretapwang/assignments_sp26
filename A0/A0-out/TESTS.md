# A0 Tests

Use the table below to describe your test cases. Each row should describe one test.

| Test name | Setup (steps) | Operation | Expected result | Exception? |
| --- | --- | --- | --- | --- |
| size on empty list | create empty list | size() | 0 | n/a |
| size on non-empty list | create empty list, add two items | size() | 2 | n/a |
| isEmpty on empty list | create empty list | isEmpty() | true | n/a |
| isEmpty on non-empty list | create empty list, add an item | isEmpty() | false | n/a |
| get on empty list | create empty list | get(0) | n/a | IndexOutOfBoundsException |
| get with index < 0 | create empty list, add an item | get(-1) | n/a | IndexOutOfBoundsException |
| get with index = 0 | create empty list, add three items | get(0) | first item | n/a |
| get with index = size - 1 | create empty list, add three items | get(2) | last item | n/a |
| get with index = size | create empty list, add an item | get(1) | n/a | IndexOutOfBoundsException |
| insert on empty list | create empty list, create item a | insert(a, 0), get(0) | a | n/a |
| insert with index < 0 | create empty list, create item a | insert(a, -1) | n/a | IndexOutOfBoundsException |
| insert with index = 0 | create empty list, create items a, b | insert(b,0), insert(a,0), get(0), get(1) | a, b respectively | n/a |
| insert with index = size | create empty list, create items a, b | insert(a,0), insert(b,1), get(0), get(1) | a, b respectively | n/a |
| insert with index = size + 1 | create empty list, create item a | insert(a, 1) | n/a | IndexOutOfBoundsException |
| remove on empty list | create empty list | remove(0) | n/a | IndexOutOfBoundsException |
| remove with index < 0 | create empty list, add an item | remove(-1) | n/a | IndexOutOfBoundsException |
| remove with index = 0 | create empty list, add item a at 0 and b at 1 | remove(0), size(), get(0) | 1, b respectively | n/a |
| remove with index = size - 1 | create empty list, add item a at 0 and b at 1 | remove(1), size(), get(0) | 1, a respectively | n/a |
| remove with index = size | create empty list, add an item | remove(1) | n/a | IndexOutOfBoundsException |