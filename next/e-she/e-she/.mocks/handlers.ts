import { rest } from 'msw'

export const handlers = [
  rest.get('/products', (_, res, ctx) => {
    return res(
      ctx.status(200),
      ctx.json([
        {
          productId: 'P2193A',
          fullName: 'separative keyboard',
          standardPrice: 13320,
          currency: 'YEN',
          stock: 'ENOUGH'
        },
        {
          productId: 'B00129',
          fullName: 'super big display',
          standardPrice: 1332320,
          currency: 'YEN',
          stock: 'ENOUGH'
        }
      ])
    )
  })
]
