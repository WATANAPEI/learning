import { Footer } from '@/components/layout/Footer'
import { BasicHtmlHead } from '@/components/layout/BasicHtmlHeader'
import { Header } from '@/components/layout/Header'
import { ProductList } from './ProductList'

export const ProductsPage = () => {
  return (
    <>
      <BasicHtmlHead
        title="Product top page"
        description="This is product top page."
      />
      <Header />
      <ProductList />
      <Footer />
    </>
  )
}
