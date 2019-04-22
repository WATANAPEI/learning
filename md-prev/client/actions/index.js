import ApiClient from "../api/ApiClient"

export const loadNewData = (data) =>  ({
  type: 'UPDATE',
  text: data.title,
  author: data.author,
  count: ++data.count //前方でないとダメ
})

export const changeText = (text) => {
    return {
    type: 'CHANGE',
    text: text
    };
};

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
