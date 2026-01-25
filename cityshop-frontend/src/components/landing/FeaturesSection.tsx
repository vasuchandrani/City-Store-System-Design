import { motion } from 'framer-motion';
import { 
  Store, Search, ShoppingCart, Truck, CreditCard, MapPin,
  Package, BarChart3, Megaphone, Bell, Shield, Clock
} from 'lucide-react';
import { Card, CardContent } from '@/components/ui/card';

const customerFeatures = [
  {
    icon: MapPin,
    title: 'City-Based Discovery',
    description: 'Browse all registered shops in your city and support local businesses.',
  },
  {
    icon: Search,
    title: 'Smart Product Search',
    description: 'Search products across multiple shops with real-time availability.',
  },
  {
    icon: ShoppingCart,
    title: 'Multi-Shop Checkout',
    description: 'Add items from multiple shops and place a single consolidated order.',
  },
  {
    icon: Truck,
    title: 'Fast Delivery',
    description: 'Quick-commerce-style fulfillment with end-to-end order tracking.',
  },
  {
    icon: Shield,
    title: 'Trusted Local Shops',
    description: 'Purchase from verified neighborhood shops you know and trust.',
  },
  {
    icon: CreditCard,
    title: 'Flexible Payments',
    description: 'Pay online or choose cash on delivery - whatever works for you.',
  },
];

const shopkeeperFeatures = [
  {
    icon: Store,
    title: 'Digital Storefront',
    description: 'Create a beautiful online presence for your shop with city-wide visibility.',
  },
  {
    icon: Package,
    title: 'Inventory Management',
    description: 'Real-time inventory tracking with instant stock updates and availability control.',
  },
  {
    icon: BarChart3,
    title: 'Analytics Dashboard',
    description: 'Track orders, revenue, and customer insights to grow your business.',
  },
  {
    icon: Megaphone,
    title: 'Marketing Tools',
    description: 'Create in-app advertisements and promotions to attract more customers.',
  },
  {
    icon: Bell,
    title: 'Instant Notifications',
    description: 'Receive real-time alerts for new orders with simple pack-and-confirm workflow.',
  },
  {
    icon: Clock,
    title: 'No Delivery Hassle',
    description: 'Focus on your products - we handle all delivery logistics for you.',
  },
];

const FeatureCard = ({ feature, index }: { feature: typeof customerFeatures[0]; index: number }) => {
  const Icon = feature.icon;
  return (
    <motion.div
      initial={{ opacity: 0, y: 20 }}
      whileInView={{ opacity: 1, y: 0 }}
      viewport={{ once: true }}
      transition={{ delay: index * 0.1 }}
    >
      <Card variant="interactive" className="h-full">
        <CardContent className="p-6">
          <div className="w-12 h-12 rounded-xl bg-brand-light flex items-center justify-center mb-4">
            <Icon className="w-6 h-6 text-brand" />
          </div>
          <h3 className="font-bold text-lg mb-2">{feature.title}</h3>
          <p className="text-sm text-muted-foreground">{feature.description}</p>
        </CardContent>
      </Card>
    </motion.div>
  );
};

export function FeaturesSection() {
  return (
    <section id="features" className="py-20 md:py-32">
      <div className="container mx-auto px-4">
        {/* Customer Features */}
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          whileInView={{ opacity: 1, y: 0 }}
          viewport={{ once: true }}
          className="text-center mb-16"
        >
          <span className="badge-brand mb-4">For Customers</span>
          <h2 className="text-3xl md:text-4xl font-bold mb-4">
            Shop Local, Delivered Fast
          </h2>
          <p className="text-lg text-muted-foreground max-w-2xl mx-auto">
            Experience the best of local shopping with modern convenience. 
            Browse trusted shops, compare products, and get everything delivered to your doorstep.
          </p>
        </motion.div>

        <div className="grid sm:grid-cols-2 lg:grid-cols-3 gap-6 mb-24">
          {customerFeatures.map((feature, index) => (
            <FeatureCard key={feature.title} feature={feature} index={index} />
          ))}
        </div>

        {/* Shopkeeper Features */}
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          whileInView={{ opacity: 1, y: 0 }}
          viewport={{ once: true }}
          className="text-center mb-16"
          id="for-shops"
        >
          <span className="badge-teal mb-4">For Shopkeepers</span>
          <h2 className="text-3xl md:text-4xl font-bold mb-4">
            Grow Your Business Digitally
          </h2>
          <p className="text-lg text-muted-foreground max-w-2xl mx-auto">
            Get a complete digital toolkit to operate and grow your business city-wide. 
            Compete with e-commerce giants while maintaining your local identity.
          </p>
        </motion.div>

        <div className="grid sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {shopkeeperFeatures.map((feature, index) => (
            <FeatureCard key={feature.title} feature={feature} index={index} />
          ))}
        </div>
      </div>
    </section>
  );
}
