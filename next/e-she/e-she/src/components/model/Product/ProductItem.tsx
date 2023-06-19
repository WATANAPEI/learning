export type ProductItemType = {
  text: string
}

export const ProductItem: React.FC<ProductItemType> = ({ text }) => {
  return (
    <aside>
      <p>{text}</p>
    </aside>
  )
}
