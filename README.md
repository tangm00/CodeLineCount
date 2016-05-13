# CodeLineCount
a tool for code lines count

功能：统计代码行数（c, c++, c#, java）

作者：tangm00

时间：2015/08/02 20:41 -- 2015/08/29 19:17

版本：1.0

修改：增加了jsp代码统计,图形显示效果的优化

时间：2016-05-11 20:00 -- 2016-05-12 09:37

版本: 1.1

UI层：
1.采用MainFrame继承JFrame类，负责UI和消息响应；
2.采用BKGJPanel继承JPanel类，负责背景设置和文字显示；

BLL层：
1.采用CodeLineCount类，负责文件操作和代码行数统计；
