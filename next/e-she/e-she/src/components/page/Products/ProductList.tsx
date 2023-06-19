import { useRouter } from 'next/router'
import { useProductList } from '@/components/hooks/Product/useProductList'

export const ProductList = () => {
  const { products, error, isLoading } = useProductList()
  const router = useRouter()
  console.log(`products: ${products}`)
  console.log(`error: ${error}`)
  console.log(`isLoading: ${isLoading}`)

  if (isLoading) return <h2>Now Loading...</h2>
  if (error) return <h2>Sorry. something went wrong.</h2>

  const addCart = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault()
    router.push('/cart')
  }

  const productItemView = products.map(p => (
    <aside key={p.productId}>
      <header>{p.fullName}</header>
      <p>productId: {p.productId}</p>
      <p>
        standardPrice: {p.standardPrice}
        {p.currency}
      </p>
      <p>stock status: {p.stock}</p>
      <button onClick={addCart}>Add cart</button>
    </aside>
  ))
  return <section>{productItemView}</section>
}
