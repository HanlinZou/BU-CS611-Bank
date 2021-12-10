# Bank

## 读写

- `src/configs/config.csv` 是配置文件，里面有货币汇率和时钟更新周期（目前是 1 秒）。

- `src/database/` 下的 `csv` 是数据库，目前有 customer、manager 和 stock，每个都有对应的负责读写操作的 DAO 类。

    还需要写账户、贷款之类的读写



## 设计模式

- Proxy Pattern

    新建和加载文件

- Singleton Pattern

    Timer、DAO

- Data Access Object (DAO) Pattern

    负责数据库读写操作

- Observer Pattern（Timer）

    需要开个线程让 timer 跑起来：
    
    ```java
    BankTimer timer = BankTimer.getInstance();
    new Thread(timer).start()
    ```

    然后 timer 就会每隔一秒输出当前时间和时间戳（用于测试），并调用每个依赖它的对象的 `onTimeChange()`。

    需要处理时间的对象应该 implements TimerObserver 的 `onTimeChange()`，并在 `onTimeChange()` 里处理加利息之类的操作，比如：

    ```java
    public class Account implements TimerObserver {
        private BankTimer timer = BankTimer.getInstance();

        public Account() {
            timer.addObserver(this);  // 添加到 timer 的通知列表
        }

        @Override
        public void onTimeChange() {
            // 加利息
        }
    }
    ```
