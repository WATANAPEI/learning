var updateCount = 2
export const loadNewData = (data) =>  ({
  type: 'UPDATE',
  text: data.title,
  author: data.author,
  count: updateCount++
})

