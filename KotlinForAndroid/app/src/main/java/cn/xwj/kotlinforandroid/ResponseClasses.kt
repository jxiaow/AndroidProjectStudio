package cn.xwj.kotlinforandroid

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-15 2018/5/15
 * Description: ResponseClasses
 */

data class ForecastResult(val city:City, val list:List<Forecast>)

data class City(val id:Long,val name:String, val coordinates: Coordinates, val )