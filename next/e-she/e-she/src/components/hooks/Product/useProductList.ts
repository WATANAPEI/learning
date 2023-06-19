import useSWR from 'swr'
import axios from 'axios'

type ProductResponseBody = {
  productId: string
  fullName: string
  shortName?: string
  standardPrice: number
  currency: Currency
  stock: StockStatus
}

type ProductResponse = {
  products: ProductResponseBody[]
  error: any
  isLoading: boolean
}

type Currency = 'YEN' | 'DOLLAR'
type StockStatus = 'ENOUGH' | 'LITTLE_LEFT' | 'EMPTY'

const PRODUCT_EP_URL =
  process.env.PRODUCT_EP_URL ?? 'http://localhost:3000/products'

const fetcher = () => axios.get(PRODUCT_EP_URL).then(res => res.data)

type useProductListType = () => ProductResponse

export const useProductList: useProductListType = () => {
  const { data: products, error, isLoading } = useSWR('/products', fetcher)
  return { products, error, isLoading }
}
