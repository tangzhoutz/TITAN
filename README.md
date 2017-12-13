
## TITAN

云集分布式全链路压测军演系统具备强大的分布式压测能力，能够在短时间内发起超大规模的压测流量。使用TITAN能够快速挖掘出业务系统的性能瓶颈，探测出业务系统的真实容量水位，有指导的在大促前进行容量规划，让系统坚如磐石。

## 整体架构

![](http://dl.iteye.com/upload/picture/pic/137625/0b5a7ad4-7a78-3ec7-b176-212fa7afaba6.jpg)

TITAN的整体架构由TITAN-Manager、TITAN-TaskService、TITAN-Agent、TITAN-Monitor，以及TITAN-DataCollect等5个子系统构成，并依赖于MySQL、Zookeeper、FTPServer，以及Redis等外围系统共同构建起一套完整的，能否在瞬间发起超大规模压测流量的分布式全链路压测军演系统。

## 功能特点

- 天生为分布式系统而生，超高的并发能力，支持对Agent（压测引擎）节点的无限海量扩容；
- 操作极其简单，上手快速，并且具备友好的交互式体验；
- 能够适配任何复杂的业务场景，支持多链路组装压测，无惧任何业务场景挑战；
- 支持对压测引擎、目标机器的可视化CPU、内存、网卡、磁盘IOPS等监控，让问题浮出水面；
- 支持定时自动化压测任务，更好的实现线上压测常态化。
- 便捷的压测引擎管理，无需运维纯手工介入（启/停）；
- 永久开源、不阉割功能，并且保证和云集内部主干版本保持一直，持续更新维护中。
 
 ## 联系我们
 
- issues：https://github.com/yunjiweidian/TITAN/issues
- blog：http://gao-xianglong.iteye.com
- email：gaoxl@yunjiweidian.com
