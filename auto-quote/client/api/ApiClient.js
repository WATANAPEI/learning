function testFetch(count){
    return fetch('https://jsonplaceholder.typicode.com/todos/' + (count + 1))
    }

const ApiClient = {

    //参考サイトからjsonファイルを取得する
    //内容について確認したければブラウザで見ればよい

    testDataCreate: (count) => {
    return testFetch(count)
        .then(response => response.json())
        .then(data => Object.assign(data,
             {author: 'author_sample' + '_' + (count + 1)},
            {count: count})) // insert some properties
    },
}

export default ApiClient