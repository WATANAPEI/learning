export const loadNewData = (data) =>  ({
  type: 'UPDATE',
  text: data.title,
  author: data.author,
  count: ++data.count //前方でないとダメ
})

