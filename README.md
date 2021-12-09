# Bank

目前用到的设计模式：

- Proxy Pattern

    新建和加载文件

- Singleton Pattern

    Timer、DAO

- Data Access Object (DAO) Pattern

    客户和行长的数据库读写，之后还要写账户之类的读写

- Observer Pattern（Timer）

    需要开个线程让 timer 跑起来：
    
    ```java
    BankTimer timer = BankTimer.getInstance();
    new Thread(timer).start()
    ```

    然后 timer 就会每隔一秒输出当前时间和时间戳（用于测试），并调用每个依赖它的对象的 `timeChange()`。

    需要处理时间的对象应该 implements TimerObserver 的 `timeChange()`，并在 `timeChange()` 里处理加利息之类的操作，比如：

    ```java
    public class Account implements TimerObserver {
        private BankTimer timer = BankTimer.getInstance();

        public Account() {
            timer.addObserver(this);  // 添加到 timer 的通知列表
        }

        @Override
        public void timeChange() {
            // 加利息
        }
    }
    ```
