 ## API

* ### Activity相关→[ActivityUtils.java]
```
isActivityExists   : 判断是否存在Activity
startActivity      : 打开Activity
getLauncherActivity: 获取入口activity
getTopActivity     : 获取栈顶Activity
checkAntiHijacking : 判断Activity是否被劫持
isFastDoubleClick  : 判断事件出发时间间隔是否超过预定值(可用来防止连续点击,预定值设置为1000ms)
```

* ### App相关→[AppUtils.java]
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

* ### 栏相关→[BarUtils.java]
```
setTransparentStatusBar: 设置透明状态栏（api大于19方可使用）
hideStatusBar          : 隐藏状态栏
getStatusBarHeight     : 获取状态栏高度
isStatusBarExists      : 判断状态栏是否存在
getActionBarHeight     : 获取ActionBar高度
showNotificationBar    : 显示通知栏
hideNotificationBar    : 隐藏通知栏
```

* ### 缓存相关→[CacheUtils.java]
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

* ### 清除相关→[CleanUtils.java]
```
cleanInternalCache   : 清除内部缓存
cleanInternalFiles   : 清除内部文件
cleanInternalDbs     : 清除内部数据库
cleanInternalDbByName: 根据名称清除数据库
cleanInternalSP      : 清除内部SP
cleanExternalCache   : 清除外部缓存
cleanCustomCache     : 清除自定义目录下的文件
```

* ### 剪贴板相关→[ClipboardUtils.java]
```
copyText  : 复制文本到剪贴板
getText   : 获取剪贴板的文本
copyUri   : 复制uri到剪贴板
getUri    : 获取剪贴板的uri
copyIntent: 复制意图到剪贴板
getIntent : 获取剪贴板的意图
```

* ### 关闭相关→[CloseUtils.java]
```
closeIO       : 关闭IO
closeIOQuietly: 安静关闭IO
```

* ### 转换相关→[ConvertUtils.java]
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

* ### 崩溃相关→[CrashUtils.java]
```
init: 初始化
```

* ### 设备相关→[DeviceUtils.java]
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

* ### 判空相关→[EmptyUtils.java]
```
isEmpty   : 判断对象是否为空
isNotEmpty: 判断对象是否非空
```

* ### 编码解码相关→[EncodeUtils.java]
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

* ### 加密解密相关→[EncryptUtils.java]
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

* ### 文件相关→[FileIOUtils.java]
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

* ### 文件相关→[FileUtils.java]
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

* ### Fragment相关→[FragmentUtils.java]
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

* ### Handler相关→[HandlerUtils.java]
```
HandlerHolder: 使用必读
```

* ### 图片相关→[ImageUtils.java]
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

* ### 意图相关→[IntentUtils.java]
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

* ### 键盘相关→[KeyboardUtils.java]
```
showSoftInput               : 动态显示软键盘
hideSoftInput               : 动态隐藏软键盘
toggleSoftInput             : 切换键盘显示与否状态
clickBlankArea2HideSoftInput: 点击屏幕空白区域隐藏软键盘
```

* ### 定位相关→[LocationUtils.java]
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

* ### 日志相关→[LogUtils.java]
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

* ### 网络相关→[NetworkUtils.java]
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
```

* ### 手机相关→[PhoneUtils.java]
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

* ### 拼音相关→[PinyinUtils.java]
```
ccs2Pinyin           : 汉字转拼音
ccs2Pinyin           : 汉字转拼音
getPinyinFirstLetter : 获取第一个汉字首字母
getPinyinFirstLetters: 获取所有汉字的首字母
getSurnamePinyin     : 根据名字获取姓氏的拼音
getSurnameFirstLetter: 根据名字获取姓氏的首字母
```

* ### 进程相关→[ProcessUtils.java]
```
getForegroundProcessName  : 获取前台线程包名
killAllBackgroundProcesses: 杀死所有的后台服务进程
killBackgroundProcesses   : 杀死后台服务进程
```

* ### 正则相关→[RegexUtils.java]
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

* ### 屏幕相关→[ScreenUtils.java]
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

* ### SD卡相关→[SDCardUtils.java]
```
isSDCardEnable: 判断SD卡是否可用
getSDCardPath : 获取SD卡路径
getDataPath   : 获取SD卡Data路径
getFreeSpace  : 计算SD卡的剩余空间
getSDCardInfo : 获取SD卡信息
```

* ### 服务相关→[ServiceUtils.java]
```
getAllRunningService: 获取所有运行的服务
startService        : 启动服务
stopService         : 停止服务
bindService         : 绑定服务
unbindService       : 解绑服务
isServiceRunning    : 判断服务是否运行
```

* ### Shell相关→[ShellUtils.java]
```
execCmd: 是否是在root下执行命令
```

* ### 尺寸相关→[SizeUtils.java]
```
dp2px, px2dp     : dp与px转换
sp2px, px2sp     : sp与px转换
applyDimension   : 各种单位转换
forceGetViewSize : 在onCreate中获取视图的尺寸
measureView      : 测量视图尺寸
getMeasuredWidth : 获取测量视图宽度
getMeasuredHeight: 获取测量视图高度
```

* ### Snackbar相关→[SnackbarUtils.java]
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

* ### SpannableString相关→[SpanUtils.java]
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

* ### SP相关→[SPUtils.java]
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

* ### 字符串相关→[StringUtils.java]
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
```

* ### 线程池相关→[ThreadPoolUtils.java]
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

* ### 时间相关→[TimeUtils.java]
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

* ### 吐司相关→[ToastUtils.java]
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

* ### 压缩相关→[ZipUtils.java]
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

* ### 弹出框相关→[DialogUtils.java]
```
AlertDialogInfo   : 新建弹出框（可以带动作）
```

* ### 加密相关→[security.java]
```
AESCoder   : aes算法
Base64     : base64
Base64Custom : base64
Converts   : hexStringToByte把16进制字符串转换成字节数组;
             bytesToHexString把字节数组转换成16进制字符串
             bytesToObject把字节数组转换为对象
             objectToBytes把可序列化对象转换成字节数组
             bcd2StrBCD码转为10进制串(阿拉伯数据)
             str2Bcd十进制串转为BCD码
DESPlus    : des算法
DesSecurity: des算法
MD5        : md5
RSACerPlus : rsa
```

* ### 日集收集→[util.log]
```
LogCollector.init(getApplicationContext()));
日志在：Environment.getExternalStorageDirectory()
    					.getAbsolutePath()+"/LhbankLog/"下

```
***

## About


## Download

Gradle:
``` groovy
compile 'com.springever:util:1.0.0'
```


## How to use

```
直接调用相关工具类静态方法
```


## Proguard

```
-keep class com.springever.util.** { *; }
-keepclassmembers class com.springever.util.** { *; }
-dontwarn com.springever.util.**
```
