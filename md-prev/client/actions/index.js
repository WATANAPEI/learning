import ApiClient from "../api/ApiClient"

export const loadNewData = (data) =>  ({
  type: 'UPDATE',
  text: data.title,
  author: data.author,
  count: ++data.count //前方でないとダメ
})

export const changeText = (text) => ({
    type: 'CHANGE',
    text: this.text
})

export function updateData(count){
  return (dispatch) => {
    ApiClient.testDataCreate(count)
            .then(data => {
                dispatch(loadNewData(data))
            })
            .catch(e => {
              
            })

  }
}
