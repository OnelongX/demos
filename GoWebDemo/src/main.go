package main

import (
	"net/http"
	"log"
	"fmt"
	"encoding/json"
	"time"
	"io/ioutil"
	"./session"
	_"./memory"

)

type BaseJsonBean struct {
	Code    int         `json:"code"`
	Data    interface{} `json:"data"`
	Message string      `json:"message"`
}

func NewBaseJsonBean() *BaseJsonBean {
	return &BaseJsonBean{}
}

type timeHandler struct {
	//zone *time.Location
}

// 我们使用两个结构体来演示自定义数据类型的JSON数据编码和解码。
type Response1 struct {
	Page   int
	Fruits []string
}
type Response2 struct {
	Page   int	`json:"page"`
	Fruits []string `json:"fruits"`
}
//大小写决定是否对外可见
//test 不可见 Test可见
type TestStruct struct {
	Test string `json:"test"`
}

var GlobalSessions *session.Manager
func init() {
	globalSessions, err := session.NewSessionManager("memory", "goSessionid", 10*60)

	if err!= nil{
		fmt.Println(err)
		return
	}

	GlobalSessions = globalSessions;

	go GlobalSessions.GC()

	if GlobalSessions == nil{
		fmt.Println("globalSessions nil")
		return
	}

	fmt.Println("globalSessions init")
}

func main() {

	log.Println("This is webserver base!")
	//时间
	myHandler := newTimeHandler("EST")
	http.Handle("/time",myHandler)
	//json
	http.Handle("/",http.HandlerFunc(loginTask))
	//post json
	http.Handle("/json",http.HandlerFunc(handleJson))
	http.Handle("/json1",http.HandlerFunc(handleJson1))

	err := http.ListenAndServe(":8888",nil)

	if err != nil {
		fmt.Println("ListenAndServe error: ", err.Error())
	}
}


func loginTask(w http.ResponseWriter, req *http.Request) {

	sess := GlobalSessions.SessionStart(w, req)
	//createtime := sess.Get("createtime")
	//if createtime == nil {
		sess.Set("createtime", time.Now().Unix())
	//} else if (createtime.(int64) + 1) < (time.Now().Unix()) {
	//	GlobalSessions.SessionDestroy(w, req)
	//	sess = GlobalSessions.SessionStart(w, req)
	// }
	ct := sess.Get("countnum")
	if ct == nil {
		sess.Set("countnum", 1)
	} else {
		sess.Set("countnum", (ct.(int) + 1))
	}


	fmt.Println(sess.Get("countnum"))
/*
	// 首先我们看一下将基础数据类型编码为JSON数据
	bolB, _ := json.Marshal(true)
	fmt.Println(string(bolB))

	intB, _ := json.Marshal(1)
	fmt.Println(string(intB))

	fltB, _ := json.Marshal(2.34)
	fmt.Println(string(fltB))

	strB, _ := json.Marshal("gopher")
	fmt.Println(string(strB))

	// 这里是将切片和字典编码为JSON数组或对象
	slcD := []string{"apple", "peach", "pear"}
	slcB, _ := json.Marshal(slcD)
	fmt.Println(string(slcB))

	mapD := map[string]int{"apple": 5, "lettuce": 7}
	mapB, _ := json.Marshal(mapD)
	fmt.Println(string(mapB))

	// JSON包可以自动地编码自定义数据类型。结果将只包括自定义
	// 类型中的可导出成员的值并且默认情况下，这些成员名称都作
	// 为JSON数据的键
	res1D := &Response1{
		Page:   1,
		Fruits: []string{"apple", "peach", "pear"}}
	res1B, _ := json.Marshal(res1D)
	fmt.Println(string(res1B))

	// 你可以使用tag来自定义编码后JSON键的名称
	res2D := &Response2{
		Page:   1,
		Fruits: []string{"apple", "peach", "pear"}}
	res2B, _ := json.Marshal(res2D)
	fmt.Println(string(res2B))

	// 现在我们看看解码JSON数据为Go数值
	byt := []byte(`{"num":6.13,"strs":["a","b"]}`)

	// 我们需要提供一个变量来存储解码后的JSON数据，这里
	// 的`map[string]interface{}`将以Key-Value的方式
	// 保存解码后的数据，Value可以为任意数据类型
	var dat map[string]interface{}

	// 解码过程，并检测相关可能存在的错误
	if err := json.Unmarshal(byt, &dat); err != nil {
		panic(err)
	}
	fmt.Println(dat)

	// 为了使用解码后map里面的数据，我们需要将Value转换为
	// 它们合适的类型，例如我们将这里的num转换为期望的float64
	num := dat["num"].(float64)
	fmt.Println(num)

	// 访问嵌套的数据需要一些类型转换
	strs := dat["strs"].([]interface{})
	str1 := strs[0].(string)
	fmt.Println(str1)

	// 我们还可以将JSON解码为自定义数据类型，这有个好处是可以
	// 为我们的程序增加额外的类型安全并且不用再在访问数据的时候
	// 进行类型断言
	str := `{"page": 1, "fruits": ["apple", "peach"]}`
	res := &Response2{}
	json.Unmarshal([]byte(str), &res)
	fmt.Println(res)
	fmt.Println(res.Fruits[0])

	// 上面的例子中，我们使用bytes和strings来进行原始数据和JSON数据
	// 之间的转换，我们也可以直接将JSON编码的数据流写入`os.Writer`
	// 或者是HTTP请求回复数据。
	enc := json.NewEncoder(os.Stdout)
	d := map[string]int{"apple": 5, "lettuce": 7}
	enc.Encode(d)

*/
	//模拟延时
	//time.Sleep(time.Second * 2)

	//获取客户端通过GET/POST方式传递的参数
	//http://stephen830.iteye.com/blog/2181648
	//http://studygolang.com/articles/2301
	req.ParseForm()
	paramUserName, found1 := req.Form["userName"]
	paramPassword, found2 := req.Form["password"]

	userName1, found3 := req.PostForm["userName"]
	if found3 {
		fmt.Println("----------------------")
		fmt.Println(len(userName1))
		fmt.Println(userName1[0])
		fmt.Println("----------------------")
		//fmt.Println(len(paramUserName))
		for _,v := range paramUserName{
			fmt.Println(v)
		}
		fmt.Println("----------------------")
	}



	var userName string
	var password string
	if !(found1 && found2) {
		//fmt.Fprint(w, "请勿非法访问")
		//return
		userName = "onelong"
		password = "123456"
	}else {
		userName = paramUserName[0]
		password = paramPassword[0]
	}


	s := "userName:" + userName + ",password:" + password
	fmt.Println(s)

	result := NewBaseJsonBean()
	result.Data = s
	if userName == "zhangsan" && password == "123456" {
		result.Code = 100
		result.Message = "登录成功"
	} else {
		result.Code = 101
		result.Message = "用户名或密码不正确"
	}

	//向客户端返回JSON数据
	bytes, _ := json.Marshal(result)
	fmt.Fprint(w, string(bytes))
}



func handleJson1(rw http.ResponseWriter, req *http.Request) {
	defer req.Body.Close()
	body, err := ioutil.ReadAll(req.Body)
	if err != nil {
		panic(err)
	}
	var t TestStruct
	err = json.Unmarshal(body, &t)
	if err != nil {
		panic(err)
	}

	//向客户端返回JSON数据
	result := NewBaseJsonBean()
	result.Data = t.Test;
	result.Code = 100
	result.Message = "成功"
	bytes, _ := json.Marshal(result)
	fmt.Fprint(rw, string(bytes))

}

func handleJson(rw http.ResponseWriter, req *http.Request) {
	defer req.Body.Close()
	decoder := json.NewDecoder(req.Body)
	var t TestStruct
	err := decoder.Decode(&t)
	if err != nil {
		panic(err)
	}
	//fmt.Println(t.Test)

	//向客户端返回JSON数据
	result := NewBaseJsonBean()
	result.Data = t.Test;
	result.Code = 100
	result.Message = "成功"
	bytes, _ := json.Marshal(result)
	fmt.Fprint(rw, string(bytes))

}

func (th *timeHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	t := time.Now().Unix()
	fmt.Println(t)
	//时间戳到具体显示的转化
	fmt.Println(time.Unix(t, 0).String())
	//带纳秒的时间戳
	t = time.Now().UnixNano()
	fmt.Println(t)
	//基本格式化的时间表示
	fmt.Println(time.Now().String())

	tm := time.Now().Format("2006-01-02 15:04:05")
	ts := time.Now().Format("2006-01-02 03:04:05")
	w.Write([]byte("The 24time is: " + tm))
	w.Write([]byte("\nThe time is: " + ts))
}

func newTimeHandler(name string) *timeHandler {
	return &timeHandler{}
}





/*
func loginHandler(w http.ResponseWriter, r *http.Request) {
	log.Println("loginHandler")
	pathInfo := strings.Trim(r.URL.Path, "/")
	parts := strings.Split(pathInfo, "/")
	var action = ""
	if len(parts) > 1 {
		action = strings.Title(parts[1]) + "Action"
	}

	login := &loginController{}
	controller := reflect.ValueOf(login)
	method := controller.MethodByName(action)
	if !method.IsValid() {
		method = controller.MethodByName(strings.Title("index") + "Action")
	}

	//requestValue := reflect.ValueOf(r)
	//responseValue := reflect.ValueOf(w)
	//method.Call([]reflect.Value{responseValue, requestValue})
}
*/


/*
func main() {
    url := "http://restapi3.apiary.io/notes"
    fmt.Println("URL:>", url)

    var jsonStr = []byte(`{"title":"Buy cheese and bread for breakfast."}`)
    req, err := http.NewRequest("POST", url, bytes.NewBuffer(jsonStr))
    req.Header.Set("X-Custom-Header", "myvalue")
    req.Header.Set("Content-Type", "application/json")

    client := &http.Client{}
    resp, err := client.Do(req)
    if err != nil {
        panic(err)
    }
    defer resp.Body.Close()

    fmt.Println("response Status:", resp.Status)
    fmt.Println("response Headers:", resp.Header)
    body, _ := ioutil.ReadAll(resp.Body)
    fmt.Println("response Body:", string(body))
}
*/