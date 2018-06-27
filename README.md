 ## Android API

* ### Activity相关→[android.ActivityUtils]
```
isActivityExists   : 判断是否存在Activity
startActivity      : 打开Activity
getLauncherActivity: 获取入口activity
getTopActivity     : 获取栈顶Activity
checkAntiHijacking : 判断Activity是否被劫持
isFastDoubleClick  : 判断事件出发时间间隔是否超过预定值(可用来防止连续点击,预定值设置为1000ms)
```

* ### App相关→[android.AppUtils]
```
isInstallApp         : 判断App是否安装
installApp           : 安装App（支持7.0）
installAppSilent     : 静默安装App
uninstallApp         : 卸载App
uninstallAppSilent   : 静默卸载App
isAppRoot            : 判断App是否有root权限
launchApp            : 打开App
getAppPackageName    : 获取App包名
getAppDetailsSettings: 获取App具体设置
getAppName           : 获取App名称
getAppIcon           : 获取App图标
getAppPath           : 获取App路径
getAppVersionName    : 获取App版本号
getAppVersionCode    : 获取App版本码
isSystemApp          : 判断App是否是系统应用
isAppDebug           : 判断App是否是Debug版本
getAppSignature      : 获取App签名
getAppSignatureSHA1  : 获取应用签名的的SHA1值
isAppForeground      : 判断App是否处于前台
getForegroundApp     : 获取前台应用包名
getAppInfo           : 获取App信息
getAppsInfo          : 获取所有已安装App信息
cleanAppData         : 清除App所有数据
isActivity           : 判断某个Intent是存在的
isLifeActivity       : 判断一个app已经启动
```

* ### 栏相关→[android.BarUtils]
```
setTransparentStatusBar: 设置透明状态栏（api大于19方可使用）
hideStatusBar          : 隐藏状态栏
getStatusBarHeight     : 获取状态栏高度
isStatusBarExists      : 判断状态栏是否存在
getActionBarHeight     : 获取ActionBar高度
showNotificationBar    : 显示通知栏
hideNotificationBar    : 隐藏通知栏
```

* ### 缓存相关→[android.CacheUtils]
```
getInstance    : 获取缓存实例
put            : 缓存中写入数据
getBytes       : 缓存中读取字节数组
getString      : 缓存中读取String
getJSONObject  : 缓存中读取JSONObject
getJSONArray   : 缓存中读取JSONArray
getBitmap      : 缓存中读取Bitmap
getDrawable    : 缓存中读取Drawable
getParcelable  : 缓存中读取Parcelable
getSerializable: 缓存中读取Serializable
getCacheSize   : 获取缓存大小
getCacheCount  : 获取缓存个数
remove         : 根据键值移除缓存
clear          : 清除所有缓存
```

* ### 清除相关→[android.CleanUtils]
```
cleanInternalCache   : 清除内部缓存
cleanInternalFiles   : 清除内部文件
cleanInternalDbs     : 清除内部数据库
cleanInternalDbByName: 根据名称清除数据库
cleanInternalSP      : 清除内部SP
cleanExternalCache   : 清除外部缓存
cleanCustomCache     : 清除自定义目录下的文件
```

* ### 剪贴板相关→[android.ClipboardUtils]
```
copyText  : 复制文本到剪贴板
getText   : 获取剪贴板的文本
copyUri   : 复制uri到剪贴板
getUri    : 获取剪贴板的uri
copyIntent: 复制意图到剪贴板
getIntent : 获取剪贴板的意图
```

* ### 转换相关→[android.ConvertUtils]
```
bytes2HexString, hexString2Bytes        : byteArr与hexString互转
chars2Bytes, bytes2Chars                : charArr与byteArr互转
memorySize2Byte, byte2MemorySize        : 以unit为单位的内存大小与字节数互转
byte2FitMemorySize                      : 字节数转合适内存大小
timeSpan2Millis, millis2TimeSpan        : 以unit为单位的时间长度与毫秒时间戳互转
millis2FitTimeSpan                      : 毫秒时间戳转合适时间长度
bytes2Bits, bits2Bytes                  : bytes与bits互转
input2OutputStream, output2InputStream  : inputStream与outputStream互转
inputStream2Bytes, bytes2InputStream    : inputStream与byteArr互转
outputStream2Bytes, bytes2OutputStream  : outputStream与byteArr互转
inputStream2String, string2InputStream  : inputStream与string按编码互转
outputStream2String, string2OutputStream: outputStream与string按编码互转
bitmap2Bytes, bytes2Bitmap              : bitmap与byteArr互转
drawable2Bitmap, bitmap2Drawable        : drawable与bitmap互转
drawable2Bytes, bytes2Drawable          : drawable与byteArr互转
view2Bitmap                             : view转Bitmap
dp2px, px2dp                            : dp与px互转
sp2px, px2sp                            : sp与px互转
```

* ### 崩溃相关→[android.CrashUtils]
```
init: 初始化
```

* ### 设备相关→[android.DeviceUtils]
```
isDeviceRooted   : 判断设备是否rooted
getSDKDescVersion: 获取设备android版本号（如1.0）
getSDKVersion    : 获取设备android版本号
getSystemVersion ：获取系统版本号
getAndroidID     : 获取设备AndroidID
getMacAddress    : 获取设备MAC地址
getManufacturer  : 获取设备厂商
getModel         : 获取设备型号
shutdown         : 关机
reboot           : 重启
reboot2Recovery  : 重启到recovery
reboot2Bootloader: 重启到bootloader
getClientDeviceInfo: 可以用于获取设备唯一标示
isCamera         : 判断相机是否可以用
getSerialNumber  : 获取序列号
getUuidNew       : 根据序列号与androidId来生成UUID

```
* ### 弹出框→[android.DialogUtils]
```
AlertDialogInfo  : 带确定按钮的提示文字
```

* ### 判空相关→[android.EmptyUtils]
```
isEmpty   : 判断对象是否为空
isNotEmpty: 判断对象是否非空
```

* ### 编码解码相关→[android.EncodeUtils]
```
urlEncode          : URL编码
urlDecode          : URL解码
base64Encode       : Base64编码
base64Encode2String: Base64编码
base64Decode       : Base64解码
base64UrlSafeEncode: Base64URL安全编码
htmlEncode         : Html编码
htmlDecode         : Html解码
```

* ### 加密解密相关→[android.EncryptUtils]
```
encryptMD2, encryptMD2ToString                        : MD2加密
encryptMD5, encryptMD5ToString                        : MD5加密
encryptMD5File, encryptMD5File2String                 : MD5加密文件
encryptSHA1, encryptSHA1ToString                      : SHA1加密
encryptSHA224, encryptSHA224ToString                  : SHA224加密
encryptSHA256, encryptSHA256ToString                  : SHA256加密
encryptSHA384, encryptSHA384ToString                  : SHA384加密
encryptSHA512, encryptSHA512ToString                  : SHA512加密
encryptHmacMD5, encryptHmacMD5ToString                : HmacMD5加密
encryptHmacSHA1, encryptHmacSHA1ToString              : HmacSHA1加密
encryptHmacSHA224, encryptHmacSHA224ToString          : HmacSHA224加密
encryptHmacSHA256, encryptHmacSHA256ToString          : HmacSHA256加密
encryptHmacSHA384, encryptHmacSHA384ToString          : HmacSHA384加密
encryptHmacSHA512, encryptHmacSHA512ToString          : HmacSHA512加密
encryptDES, encryptDES2HexString, encryptDES2Base64   : DES加密
decryptDES, decryptHexStringDES, decryptBase64DES     : DES解密
encrypt3DES, encrypt3DES2HexString, encrypt3DES2Base64: 3DES加密
decrypt3DES, decryptHexString3DES, decryptBase64_3DES : 3DES解密
encryptAES, encryptAES2HexString, encryptAES2Base64   : AES加密
decryptAES, decryptHexStringAES, decryptBase64AES     : AES解密
```

* ### Fragment相关→[android.FragmentUtils]
```
addFragment              : 新增fragment
hideAddFragment          : 先隐藏后新增fragment
addFragments             : 新增多个fragment
removeFragment           : 移除fragment
removeToFragment         : 移除到指定fragment
removeFragments          : 移除同级别fragment
removeAllFragments       : 移除所有fragment
replaceFragment          : 替换fragment
popFragment              : 出栈fragment
popToFragment            : 出栈到指定fragment
popFragments             : 出栈同级别fragment
popAllFragments          : 出栈所有fragment
popAddFragment           : 先出栈后新增fragment
hideFragment             : 隐藏fragment
hideFragments            : 隐藏同级别fragment
showFragment             : 显示fragment
hideShowFragment         : 先隐藏后显示fragment
getLastAddFragment       : 获取同级别最后加入的fragment
getLastAddFragmentInStack: 获取栈中同级别最后加入的fragment
getTopShowFragment       : 获取顶层可见fragment
getTopShowFragmentInStack: 获取栈中顶层可见fragment
getFragments             : 获取同级别fragment
getFragmentsInStack      : 获取栈中同级别fragment
getAllFragments          : 获取所有fragment
getAllFragmentsInStack   : 获取栈中所有fragment
getPreFragment           : 获取目标fragment的前一个fragment
findFragment             : 查找fragment
dispatchBackPress        : 处理fragment回退键
setBackgroundColor       : 设置背景色
setBackgroundResource    : 设置背景资源
setBackground            : 设置背景
```

* ### Handler相关→[android.HandlerUtils]
```
HandlerHolder: 使用必读
```

* ### 图片相关→[android.ImageUtils]
```
bitmap2Bytes, bytes2Bitmap      : bitmap与byteArr互转
drawable2Bitmap, bitmap2Drawable: drawable与bitmap互转
drawable2Bytes, bytes2Drawable  : drawable与byteArr互转
getBitmap                       : 获取bitmap
scale                           : 缩放图片
clip                            : 裁剪图片
skew                            : 倾斜图片
rotate                          : 旋转图片
getRotateDegree                 : 获取图片旋转角度
toRound                         : 转为圆形图片
toRoundCorner                   : 转为圆角图片
fastBlur                        : 快速模糊
renderScriptBlur                : renderScript模糊图片
stackBlur                       : stack模糊图片
addFrame                        : 添加颜色边框
addReflection                   : 添加倒影
addTextWatermark                : 添加文字水印
addImageWatermark               : 添加图片水印
toAlpha                         : 转为alpha位图
toGray                          : 转为灰度图片
save                            : 保存图片
isImage                         : 根据文件名判断文件是否为图片
getImageType                    : 获取图片类型
compressByScale                 : 按缩放压缩
compressByQuality               : 按质量压缩
compressBySampleSize            : 按采样大小压缩
bitmapToBase64                  : bitmap转base64
createImageThumbnail			: 获取缩略图
```

* ### 意图相关→[android.IntentUtils]
```
getInstallAppIntent        : 获取安装App（支持6.0）的意图
getUninstallAppIntent      : 获取卸载App的意图
getLaunchAppIntent         : 获取打开App的意图
getAppDetailsSettingsIntent: 获取App具体设置的意图
getShareTextIntent         : 获取分享文本的意图
getShareImageIntent        : 获取分享图片的意图
getComponentIntent         : 获取其他应用组件的意图
getShutdownIntent          : 获取关机的意图
getCaptureIntent           : 获取拍照的意图
```

* ### 键盘相关→[android.KeyboardUtils]
```
showSoftInput               : 动态显示软键盘
hideSoftInput               : 动态隐藏软键盘
toggleSoftInput             : 切换键盘显示与否状态
clickBlankArea2HideSoftInput: 点击屏幕空白区域隐藏软键盘
```

* ### 定位相关→[android.LocationUtils]
```
isGpsEnabled     : 判断Gps是否可用
isLocationEnabled: 判断定位是否可用
openGpsSettings  : 打开Gps设置界面
register         : 注册
unregister       : 注销
getAddress       : 根据经纬度获取地理位置
getCountryName   : 根据经纬度获取所在国家
getLocality      : 根据经纬度获取所在地
getStreet        : 根据经纬度获取所在街道
isBetterLocation : 是否更好的位置
isSameProvider   : 是否相同的提供者
```

* ### 日志相关→[android.LogUtils]
```
Builder.setLogSwitch     : 设置log总开关
Builder.setConsoleSwitch : 设置log控制台开关
Builder.setGlobalTag     : 设置log全局tag
Builder.setLogHeadSwitch : 设置log头部信息开关
Builder.setLog2FileSwitch: 设置log文件开关
Builder.setDir           : 设置log文件存储目录
Builder.setBorderSwitch  : 设置log边框开关
Builder.setConsoleFilter : 设置log控制台过滤器
Builder.setFileFilter    : 设置log文件过滤器
v                        : Verbose日志
d                        : Debug日志
i                        : Info日志
w                        : Warn日志
e                        : Error日志
a                        : Assert日志
file                     : log到文件
json                     : log字符串之json
xml                      : log字符串之xml
```

* ### 网络相关→[android.NetworkUtils]
```
openWirelessSettings  : 打开网络设置界面
isConnected           : 判断网络是否连接
isAvailableByPing     : 判断网络是否可用
getDataEnabled        : 判断移动数据是否打开
setDataEnabled        : 打开或关闭移动数据
is4G                  : 判断网络是否是4G
getWifiEnabled        : 判断wifi是否打开
setWifiEnabled        : 打开或关闭wifi
isWifiConnected       : 判断wifi是否连接状态
isWifiAvailable       : 判断wifi数据是否可用
getNetworkOperatorName: 获取移动网络运营商名称
getNetworkType        : 获取当前网络类型
getIPAddress          : 获取IP地址
getDomainAddress      : 获取域名ip地址
intToIp               : int的ip转成ip字符串
```

* ### 手机相关→[android.PhoneUtils]
```
isPhone            : 判断设备是否是手机
getIMEI            : 获取IMEI码
getIMSI            : 获取IMSI码
getPhoneType       : 获取移动终端类型
isSimCardReady     : 判断sim卡是否准备好
getSimOperatorName : 获取Sim卡运营商名称
getSimOperatorByMnc: 获取Sim卡运营商名称
getPhoneStatus     : 获取手机状态信息
dial               : 跳至拨号界面
call               : 拨打phoneNumber
sendSms            : 跳至发送短信界面
sendSmsSilent      : 发送短信
getAllContactInfo  : 获取手机联系人
getContactNum      : 打开手机联系人界面点击联系人后便获取该号码
getAllSMS          : 获取手机短信并保存到xml中
```

* ### 拼音相关→[SimpleArrayMapPinyinUtils]
```
ccs2Pinyin           : 汉字转拼音
ccs2Pinyin           : 汉字转拼音
getPinyinFirstLetter : 获取第一个汉字首字母
getPinyinFirstLetters: 获取所有汉字的首字母
getSurnamePinyin     : 根据名字获取姓氏的拼音
getSurnameFirstLetter: 根据名字获取姓氏的首字母
```

* ### 进程相关→[SimpleArrayMapProcessUtils]
```
getForegroundProcessName  : 获取前台线程包名
killAllBackgroundProcesses: 杀死所有的后台服务进程
killBackgroundProcesses   : 杀死后台服务进程
```

* ### 屏幕相关→[android.ScreenUtils]
```
getScreenWidth         : 获取屏幕的宽度（单位：px）
getScreenHeight        : 获取屏幕的高度（单位：px）
setLandscape           : 设置屏幕为横屏
setPortrait            : 设置屏幕为竖屏
isLandscape            : 判断是否横屏
isPortrait             : 判断是否竖屏
getScreenRotation      : 获取屏幕旋转角度
captureWithStatusBar   : 获取当前屏幕截图，包含状态栏
captureWithoutStatusBar: 获取当前屏幕截图，不包含状态栏
isScreenLock           : 判断是否锁屏
```

* ### SD卡相关→[android.SDCardUtils]
```
isSDCardEnable: 判断SD卡是否可用
getSDCardPath : 获取SD卡路径
getDataPath   : 获取SD卡Data路径
getFreeSpace  : 计算SD卡的剩余空间
getSDCardInfo : 获取SD卡信息
```

* ### 服务相关→[android.ServiceUtils]
```
getAllRunningService: 获取所有运行的服务
startService        : 启动服务
stopService         : 停止服务
bindService         : 绑定服务
unbindService       : 解绑服务
isServiceRunning    : 判断服务是否运行
```

* ### 尺寸相关→[android.SizeUtils]
```
dp2px, px2dp     : dp与px转换
sp2px, px2sp     : sp与px转换
applyDimension   : 各种单位转换
forceGetViewSize : 在onCreate中获取视图的尺寸
measureView      : 测量视图尺寸
getMeasuredWidth : 获取测量视图宽度
getMeasuredHeight: 获取测量视图高度
```

* ### Snackbar相关→[android.SnackbarUtils]
```
with           : 设置snackbar依赖view
setMessage     : 设置消息
setMessageColor: 设置消息颜色
setBgColor     : 设置背景色
setBgResource  : 设置背景资源
setDuration    : 设置显示时长
setAction      : 设置行为
show           : 显示snackbar
showSuccess    : 显示预设成功的snackbar
showWarning    : 显示预设警告的snackbar
showError      : 显示预设错误的snackbar
dismiss        : 消失snackbar
getView        : 获取snackbar视图
addView        : 添加snackbar视图
```

* ### SpannableString相关→[android.SpanUtils]
```
setFlag           : 设置标识
setForegroundColor: 设置前景色
setBackgroundColor: 设置背景色
setLineHeight     : 设置行高
setQuoteColor     : 设置引用线的颜色
setLeadingMargin  : 设置缩进
setBullet         : 设置列表标记
setIconMargin     : 设置图标
setFontSize       : 设置字体尺寸
setFontProportion : 设置字体比例
setFontXProportion: 设置字体横向比例
setStrikethrough  : 设置删除线
setUnderline      : 设置下划线
setSuperscript    : 设置上标
setSubscript      : 设置下标
setBold           : 设置粗体
setItalic         : 设置斜体
setBoldItalic     : 设置粗斜体
setFontFamily     : 设置字体系列
setTypeface       : 设置字体
setAlign          : 设置对齐
setClickSpan      : 设置点击事件
setUrl            : 设置超链接
setBlur           : 设置模糊
setShader         : 设置着色器
setShadow         : 设置阴影
setSpans          : 设置样式
append            : 追加样式字符串
appendLine        : 追加一行样式字符串
appendImage       : 追加图片
appendSpace       : 追加空白
create            : 创建样式字符串
```

* ### SP相关→[android.SharedPreferenceUtils]
```
getInstance: 获取SP实例
put        : SP中写入数据
getString  : SP中读取String
getInt     : SP中读取int
getLong    : SP中读取long
getFloat   : SP中读取float
getBoolean : SP中读取boolean
getAll     : SP中获取所有键值对
contains   : SP中是否存在该key
remove     : SP中移除该key
clear      : SP中清除所有数据
```

* ### 线程池相关→[android.ThreadPoolUtils]
```
ThreadPoolUtils       : ThreadPoolUtils构造函数
execute               : 在未来某个时间执行给定的命令
execute               : 在未来某个时间执行给定的命令链表
shutDown              : 待以前提交的任务执行完毕后关闭线程池
shutDownNow           : 试图停止所有正在执行的活动任务
isShutDown            : 判断线程池是否已关闭
isTerminated          : 关闭线程池后判断所有任务是否都已完成
awaitTermination      : 请求关闭、发生超时或者当前线程中断
submit                : 提交一个Callable任务用于执行
submit                : 提交一个Runnable任务用于执行
invokeAll, invokeAny  : 执行给定的任务
schedule              : 延迟执行Runnable命令
schedule              : 延迟执行Callable命令
scheduleWithFixedRate : 延迟并循环执行命令
scheduleWithFixedDelay: 延迟并以固定休息时间循环执行命令
```

* ### 时间相关→[android.TimeUtils]
```
millis2String           : 将时间戳转为时间字符串
string2Millis           : 将时间字符串转为时间戳
string2Date             : 将时间字符串转为Date类型
date2String             : 将Date类型转为时间字符串
date2Millis             : 将Date类型转为时间戳
millis2Date             : 将时间戳转为Date类型
getTimeSpan             : 获取两个时间差（单位：unit）
getFitTimeSpan          : 获取合适型两个时间差
getNowMills             : 获取当前毫秒时间戳
getNowString            : 获取当前时间字符串
getNowDate              : 获取当前Date
getTimeSpanByNow        : 获取与当前时间的差（单位：unit）
getFitTimeSpanByNow     : 获取合适型与当前时间的差
getFriendlyTimeSpanByNow: 获取友好型与当前时间的差
getMillis               : 获取与给定时间等于时间差的时间戳
getString               : 获取与给定时间等于时间差的时间字符串
getDate                 : 获取与给定时间等于时间差的Date
getMillisByNow          : 获取与当前时间等于时间差的时间戳
getStringByNow          : 获取与当前时间等于时间差的时间字符串
getDateByNow            : 获取与当前时间等于时间差的Date
isToday                 : 判断是否今天
isLeapYear              : 判断是否闰年
getChineseWeek          : 获取中式星期
getUSWeek               : 获取美式式星期
getWeekIndex            : 获取星期索引
getWeekOfMonth          : 获取月份中的第几周
getWeekOfYear           : 获取年份中的第几周
getChineseZodiac        : 获取生肖
getZodiac               : 获取星座
dateAdd                 : 根据初始时间和相隔时间计算最终时间
dateDiff                : 计算两个时间相差多少时间
getDateTime             : 获取当前时间（yyyy/MM/dd HH:mm:ss）
getFormatDate           : 格式化时间（yyyy/MM/dd HH:mm:ss）
getTwoDay               : 获取连个时间相差的天数，支持yyyy/MM/dd与yyyy-MM-dd
getDateFutureOrBefore   : 根据初始时间和相隔天数计算最终时间
```

* ### 吐司相关→[android.ToastUtils]
```
setGravity           : 设置吐司位置
setView              : 设置吐司view
getView              : 获取吐司view
setBackgroundColor   : 设置背景颜色
setBackgroundResource: 设置背景资源
setMessageColor      : 设置消息颜色
showShortSafe        : 安全地显示短时吐司
showLongSafe         : 安全地显示长时吐司
showShort            : 显示短时吐司
showLong             : 显示长时吐司
cancel               : 取消吐司显示
```

* ### uuid相关→[android.UuidUtils]
```
第一步:UuidUtils.buidleID(context).check();
第二步:String uuid= UuidUtils.getUUID()
```

* ### 日集收集→[adnroid.log]
```
LogCollector.init(getApplicationContext()));
日志在：Environment.getExternalStorageDirectory()
    					.getAbsolutePath()+"/LhbankLog/"下
```
***

 ## Java API

* ### 文件相关→[java.FileIOUtils]
```
writeFileFromIS            : 将输入流写入文件
writeFileFromBytesByStream : 将字节数组写入文件
writeFileFromBytesByChannel: 将字节数组写入文件
writeFileFromBytesByMap    : 将字节数组写入文件
writeFileFromString        : 将字符串写入文件
readFile2List              : 读取文件到字符串链表中
readFile2String            : 读取文件到字符串中
readFile2BytesByStream     : 读取文件到字节数组中
readFile2BytesByChannel    : 读取文件到字节数组中
readFile2BytesByMap        : 读取文件到字节数组中
setBufferSize              : 设置缓冲区尺寸
```

* ### 查找区域→[java.AddressUtils]
```
getAddresses	: 根据ip查找地址区域
```

* ### 农历与公历→[java.LunarUtils]
```
lunarYearToGanZhi   : 农历转天干地支
LunarToSolar        : 农历转公历
SolarToLunar        : 公历转农历
```

* ### 字符串相关→[java.StringUtils]
```
isEmpty         : 判断字符串是否为null或长度为0
isTrimEmpty     : 判断字符串是否为null或全为空格
isSpace         : 判断字符串是否为null或全为空白字符
equals          : 判断两字符串是否相等
equalsIgnoreCase: 判断两字符串忽略大小写是否相等
null2Length0    : null转为长度为0的字符串
length          : 返回字符串长度
upperFirstLetter: 首字母大写
lowerFirstLetter: 首字母小写
reverse         : 反转字符串
toDBC           : 转化为半角字符
toSBC           : 转化为全角字符
formatString    : 格式化字符串 a={0}&b={1}
formatStr       : 格式化字符串 %s本次注册验证码为：%s
formatStrFlag   : 根据位置来格式化字符串 %1$s已经安装成功
```

* ### Shell相关→[java.ShellUtils]
```
execCmd: 是否是在root下执行命令
chmodFile :执行shell脚本
```

* ### 正则相关→[java.RegexUtils]
```
isMobileSimple : 验证手机号（简单）
isMobileExact  : 验证手机号（精确）
isTel          : 验证电话号码
isIDCard15     : 验证身份证号码15位
isIDCard18     : 验证身份证号码18位
isEmail        : 验证邮箱
isURL          : 验证URL
isZh           : 验证汉字
isUsername     : 验证用户名
isDate         : 验证yyyy-MM-dd格式的日期校验，已考虑平闰年
isIP           : 验证IP地址
isMatch        : 判断是否匹配正则
getMatches     : 获取正则匹配的部分
getSplits      : 获取正则匹配分组
getReplaceFirst: 替换正则匹配的第一部分
getReplaceAll  : 替换所有正则匹配的部分
```

* ### uuid相关→[java.UuidUtils]
```
getUUID        : 生成一个128位的唯一标识符
getUserid      : 生成一个用户id
randomPassword : 只有数字(0-9)或包含数字和字母(小写)
```

* ### uuid相关→[java.DateUtils]
```
dateAdd                 : 根据初始时间和相隔时间计算最终时间
dateDiff                : 计算两个时间相差多少时间
getDateTime             : 获取当前时间（yyyy/MM/dd HH:mm:ss）
getFormatDate           : 格式化时间（yyyy/MM/dd HH:mm:ss）
getTwoDay               : 获取连个时间相差的天数，支持yyyy/MM/dd与yyyy-MM-dd
getDateFutureOrBefore   : 根据初始时间和相隔天数计算最终时间
maxDayOfMonth           : 获取每个月的天数
getLastMonthDate        : 获取指定年月上月月末日期
getLastMonthDate        : 获取指定年月上月月末日期
beforeDate              : 提前N天的日期
addHour                 : 提前N个小时的日期
getLastWeek             : 一周前的日期
getNextDay              : 取当前相对前后的天数，正数为向后，负数为向前
getWeekNumOfYear        : 计算当前日期某年的第几周
getWeekNumOfYearDay     : 计算指定日期某年的第几周
getWeekFirstDay         : 计算某年某周的开始日期
getWeekEndDay           : 计算某年某周的结束日期
getDate                 : 获取当前日期
getTime                 : 获取当前时间
getNextWeekDay          : 获取指定日期的下一周日期
getCurrWeekDay          : 获取指定日期的当前一周日期
getCurrMonthDay         : 获取指定日期的下个月日期
getNextMonthDay         : 获取指定日期的下个月日期
beforeMonthDate         : 前几个月日期
getPlanWeekDay          : 获取指定日期的预约日期
getPlanMonthDay         : 获取指定日期的预约日期
```

* ### aes128算法相关→[java.security.AesCbcUtils]
```
encrypt         : 加密（外包Base64算法）
decrypt         : 解密
```

* ### aes128算法相关→[java.security.AESCoder]
```
toKey           : 转换明文秘钥
encrypt         : 加密（外包Base64算法）
decrypt         : 解密
```

* ### aes128算法相关→[java.security.AesUtils]
```
encrypt         : 加密（外包Base64算法）
decrypt         : 解密
```

* ### Base64算法相关→[java.security.Base64]
```
encode           : 编码
decode           : 解码
```

* ### Base64算法相关→[java.security.Base64Custom]
```
encode           :编码
decode           :解码
```

* ### 证书相关→[java.security.ConventPFX]
```
coverTokeyStore          : 生成jks证书文件
coverToPfx               : 生成pfx证书文件
```

* ### 转换相关→[java.security.Converts]
```
hexStringToByte  : 把16进制字符串转换成字节数组;
bytesToHexString : 把字节数组转换成16进制字符串
bytesToObject    : 把字节数组转换为对象
objectToBytes    : 把可序列化对象转换成字节数组
bcd2Str          : BCD码转为10进制串(阿拉伯数据)
str2Bcd          : 十进制串转为BCD码
byteArr2HexStr   : 字节数组转16进制字符串
hexstr2ByteArr   : 16进制字符串转字节数组
toHexString      : 16进制字节转换成16进制字符串表示
hexStringToBytes : 16进制字符串转换成16进制字节表示
stringToChinese  : 汉字转unicode
chineseToString  : unicode转汉字
```

* ### DES相关→[java.security.DESPlus]
```
getInstance     : 获取单例
encrypt         : 加密
decrypt         : 解密
```

* ### DES相关→[java.security.DesSecurity]
```
encrypt         : 加密
decrypt         : 解密
```

* ### 加密解密相关→[java.security.EncryptUtils]
```
encryptMD2, encryptMD2ToString                        : MD2加密
encryptMD5, encryptMD5ToString                        : MD5加密
encryptMD5File, encryptMD5File2String                 : MD5加密文件
encryptSHA1, encryptSHA1ToString                      : SHA1加密
encryptSHA224, encryptSHA224ToString                  : SHA224加密
encryptSHA256, encryptSHA256ToString                  : SHA256加密
encryptSHA384, encryptSHA384ToString                  : SHA384加密
encryptSHA512, encryptSHA512ToString                  : SHA512加密
encryptHmacMD5, encryptHmacMD5ToString                : HmacMD5加密
encryptHmacSHA1, encryptHmacSHA1ToString              : HmacSHA1加密
encryptHmacSHA224, encryptHmacSHA224ToString          : HmacSHA224加密
encryptHmacSHA256, encryptHmacSHA256ToString          : HmacSHA256加密
encryptHmacSHA384, encryptHmacSHA384ToString          : HmacSHA384加密
encryptHmacSHA512, encryptHmacSHA512ToString          : HmacSHA512加密
encryptDES, encryptDES2HexString, encryptDES2Base64   : DES加密
decryptDES, decryptHexStringDES, decryptBase64DES     : DES解密
encrypt3DES, encrypt3DES2HexString, encrypt3DES2Base64: 3DES加密
decrypt3DES, decryptHexString3DES, decryptBase64_3DES : 3DES解密
encryptAES, encryptAES2HexString, encryptAES2Base64   : AES加密
decryptAES, decryptHexStringAES, decryptBase64AES     : AES解密
```

* ### md5相关→[java.security.MD5]
```
getMD5                  : 获取md值
```

* ### Pin Block加解密相关→[java.security.PinBlockUtil]
```
encrypt                  : 生成加密数组
decrypt                  : 解密加密数组
getHPin                  : pin生成加密数组
getHAccno                : accno生成加密数组
```

* ### 加载证书相关→[java.security.RSACerPlus]
```
getInstance              : 获取单例
initCer                  : 初始化证书
doEncrypt                : 用公钥加密
```

* ### RSA算法相关→[java.security.RsaUtils]
```
createKeyPairs           : 创建公钥与私钥
encrypt                  : 公钥加密
decrypt                  : 私钥解密
```

* ### SHA算法相关→[java.security.ShaUtils]
```
sign                     : sha-1
digest                   : sha-1
sha256                   : sha256
```

* ### ftp相关→[java.network.ftp.FTPCommon]
```
1、FTPModel赋值
2、new FTPCommon(FTPModel);
3、ftpLogin登录
4、down样式的方法为下载；upload样式的方法为上传
5、最后close方法关闭ftp
ftpLogin            : 登录ftp
isOpenFTPConnection : 检查FTP服务器是否关闭 ，如果关闭接则连接登录FTP
close               : 关闭ftp
setFileType         : 设置传输文件的类型[文本文件或者二进制文件]
downloadFile        : 下载文件
uploadFile          : 上传文件
pwd                 : 当前目录
changeDir           : 变更工作目录
toParentDir         : 返回上一级目录
createDir           : 创建目录
getListFiels        : 获得FTP 服务器下所有的文件名列表
```

* ### uuid相关→[java.network.http.SimpleHttpClient]
```
httpPostOrGet: 简单的post、get请求，附带文件上传
```

* ### uuid相关→[java.JsonUtils]
```
xmlStrToJsonStr : XML转成JSON
mapToJsonStr    : MAP转成JSON
jsonStrToXmlStr : JSON转成XML
```

* ### xml相关→[java.XmlUtils]
```
readXml             : String转化成XML
readText            : String转换成Document形成的XML
formatXml           : 格式化字符串XML
jsonStrToXmlStr     : JSON转换成XML
jsonToElement       : JSON转换成Element形成的XML
validateXMLByXSD    : 通过XSD检验XML
```

* ### xml报文相关→[java.message.MessageTools]
```
removeElements      : 删除String中的某个标签
getElements         : XML转换成MAP
jsonToMap           : JSON转换成MAP
jsonToElement       : JSON转换成Element形成的XML
jsonToString        : JSON转换成String
elementToMap        : Element形成的XML转换成MAP
mapToElement        : MAP转换成Element形成的XML
loadXmlByFile       : 加载文件形成Documeng形成的XML
```

* ### pdf相关→[java.PdfManagerUtils]
```
pdf                 : 根据图片生成pdf
mergePdfFiles       : 合并pdf
```

* ### 压缩相关→[java.ZipUtils]
```
zipFiles          : 批量压缩文件
zipFile           : 压缩文件
unzipFiles        : 批量解压文件
unzipFile         : 解压文件
unzipFileByKeyword: 解压带有关键字的文件
getFilesPath      : 获取压缩文件中的文件路径链表
getComments       : 获取压缩文件中的注释链表
getEntries        : 获取压缩文件中的文件对象
```

* ### 文件相关→[java.FileUtils]
```
getFileByPath            : 根据文件路径获取文件
isFileExists             : 判断文件是否存在
rename                   : 重命名文件
isDir                    : 判断是否是目录
isFile                   : 判断是否是文件
createOrExistsDir        : 判断目录是否存在，不存在则判断是否创建成功
createOrExistsFile       : 判断文件是否存在，不存在则判断是否创建成功
createFileByDeleteOldFile: 判断文件是否存在，存在则在创建之前删除
copyDir                  : 复制目录
copyFile                 : 复制文件
moveDir                  : 移动目录
moveFile                 : 移动文件
deleteDir                : 删除目录
deleteFile               : 删除文件
listFilesInDir           : 获取目录下所有文件
listFilesInDir           : 获取目录下所有文件包括子目录
listFilesInDirWithFilter : 获取目录下所有后缀名为suffix的文件
listFilesInDirWithFilter : 获取目录下所有后缀名为suffix的文件包括子目录
listFilesInDirWithFilter : 获取目录下所有符合filter的文件
listFilesInDirWithFilter : 获取目录下所有符合filter的文件包括子目录
searchFileInDir          : 获取目录下指定文件名的文件包括子目录
getFileLastModified      : 获取文件最后修改的毫秒时间戳
getFileCharsetSimple     : 简单获取文件编码格式
getFileLines             : 获取文件行数
getDirSize               : 获取目录大小
getFileSize              : 获取文件大小
getDirLength             : 获取目录长度
getFileLength            : 获取文件长度
getFileMD5               : 获取文件的MD5校验码
getFileMD5ToString       : 获取文件的MD5校验码
getDirName               : 根据全路径获取最长目录
getFileName              : 根据全路径获取文件名
getFileNameNoExtension   : 根据全路径获取文件名不带拓展名
getFileExtension         : 根据全路径获取文件拓展名
```

* ### ip相关→[java.IpUtils]
```
getIpAddr                : 获取客户端真实的ip
```

* ### url加载相关→[java.http.UrlClient]
```
execute                  : 给url发送报文
decrypt                  : 解密加密数组
getHPin                  : pin生成加密数组
getHAccno                : accno生成加密数组
```

* ### 树排序相关→[java.sort.TreeSortUtils]
```
sort4Tree                : 按树结构进行排序
```

* ### token相关→[java.AccessTokenUtil]
```
generateAccessToken      : 获取token
```
***

* ### token相关→[java.db.Mysql]
```
connectMysql              : 简单建立mysql连接
```

* ### 关闭相关→[java.CloseUtils]
```
closeIO       : 关闭IO
closeIOQuietly: 安静关闭IO
```
***

## 关于
分为android与java两部分，android内容为util.android;java内容为util.java

## 如何获得

gradle:
```
implementation 'com.github.Springever:LhbankUtil:1.2.2'
```

maven:
```
<repositories>
	<repository>
		 <id>jitpack.io</id>
		 <url>https://jitpack.io</url>
	</repository>
</repositories>

<dependency>
	<groupId>com.github.Springever</groupId>
	<artifactId>LhbankUtil</artifactId>
	<version>1.2.2</version>
</dependency>
```
## 如何使用

```
android:需要先调用android.Utils初始化Context;
然后直接调用相关工具类静态方法
```


## Proguard混淆

```
-dontwarn com.lhbank.commons.**
-dontwarn com.lhbank.http.**
-dontwarn com.lhbank.itextpdf.**
-dontwarn com.lhbank.json.**
-dontwarn org.dom4j.**
-dontwarn com.lhbank.cfca.mobile.hke.**

-keep class com.springever.util.java.XmlUtils{*;}
```
